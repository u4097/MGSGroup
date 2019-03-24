package com.apptimizm.mgs.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.ErrorResponseEntity
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.datasource.model.route.RouteUpdaterEntity
import com.apptimizm.mgs.domain.model.route.RouteResponse
import com.apptimizm.mgs.domain.usecases.RouteUseCase
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-16
 * Time: 09:22
 */


class RouteAllViewModel constructor(val routeUseCase: RouteUseCase) : AbstractViewModel() {

    val pending = AtomicBoolean(false)

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }


    val routeResult = MutableLiveData<Resource<RouteResponse>>()

    val routeSize = MutableLiveData<Int>()

    var route: LiveData<RouteEntity>? = null


    val routes: LiveData<PagedList<RouteEntity>> = Transformations.switchMap(
        routeResult
    ) {
        it.data?.results
    }

    val networkErrors: LiveData<String> = Transformations.switchMap(
        routeResult
    ) { it.data?.networkErrors }

    val serverError = MutableLiveData<ErrorResponseEntity>()

    /**
     * Get all routes from cache.
     */
    fun getRoutesFromCache() {
        routeResult.postValue(routeUseCase.getRoutesFromCache())
    }



    fun getRoutesFromServer(refresh: Boolean = false) {
        scope.launch {
            if (pending.compareAndSet(false, true)) {
                routeUseCase.getRouteFromServer(refresh = refresh, onSuccess = { routeSize.postValue(it) }) {
                    serverError.postValue(it)
                }
            }
        }
    }


    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            scope.launch {
                routeUseCase.getRouteFromServer(refresh = false, onSuccess = { routeSize.postValue(it) }) {
                    serverError.postValue(it)
                }
            }
        }
    }
}


