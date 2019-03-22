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


class RouteViewModel constructor(val routeUseCase: RouteUseCase) : AbstractViewModel() {

    val pending = AtomicBoolean(false)

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }


    val routeResult = MutableLiveData<Resource<RouteResponse>>()

    var route : LiveData<RouteEntity>? = null


    val routes: LiveData<PagedList<RouteEntity>> = Transformations.switchMap(
        routeResult
    ) {
//        Timber.tag("ROUTE").d("Transform.swithcMap on routeResult")
        it.data?.results
    }

    val networkErrors: LiveData<String> = Transformations.switchMap(
        routeResult
    ) { it.data?.networkErrors }

    val serverError = MutableLiveData<ErrorResponseEntity>()

    /**
     * Refresh routes from  repository based.
     */
    fun getRoutesFromCache() {
        routeResult.postValue(routeUseCase.getRoutesFromCache())
    }

    /** Get routes from cache by status */
    fun getRoutesFromCacheByStatus(status: String) {
        routeResult.postValue(routeUseCase.getRoutesFromCacheByStatus(status))
    }

    fun getRoutesFromCacheById(routeId: String) {
        route =  routeUseCase.getRouteFromCacheById(routeId)
    }


    fun getRoutesFromServer(refresh: Boolean = false) {
        scope.launch {
            if (pending.compareAndSet(false, true)) {
                routeUseCase.getRouteFromServer(refresh = refresh) {
                    serverError.postValue(it)
                }
            }
        }
    }

    fun updateRoute(route: RouteUpdaterEntity, id: String?) {
        scope.launch {
            if (pending.compareAndSet(false, true)) {
                routeUseCase.updateRouteOnServer(route = route, id = id) {
                    serverError.postValue(it)
                }
            }
        }

    }


    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            scope.launch {
                if (pending.compareAndSet(false, true)) {
                    routeUseCase.getRouteFromServer(refresh = false) {
                        serverError.postValue(it)
                    }
                }
            }
        }
    }
}


