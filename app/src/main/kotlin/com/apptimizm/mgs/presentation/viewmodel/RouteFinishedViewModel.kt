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


class RouteFinishedViewModel constructor(val routeUseCase: RouteUseCase) : AbstractViewModel() {

    override val pending = AtomicBoolean(false)

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

    /** Get routes from cache by status */
    fun getRoutesFromCacheByStatus(status: String) {
        routeResult.postValue(routeUseCase.getRoutesFromCacheByStatus(status))
    }


    override fun getRoutesFromServer(refresh: Boolean) {
        scope.launch {
            if (pending.compareAndSet(false, true)) {
                routeUseCase.getRouteFromServer(refresh = refresh, onSuccess = { routeSize.postValue(it) }) {
                    serverError.postValue(it)
                }
            }
        }
    }


    override fun listScrolled() {
        scope.launch {
            routeUseCase.getRouteFromServer(refresh = false, onSuccess = { routeSize.postValue(it) }) {
                serverError.postValue(it)
            }
        }
    }
}


