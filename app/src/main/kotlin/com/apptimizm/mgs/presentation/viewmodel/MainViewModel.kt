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
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-16
 * Time: 09:22
 */


class MainViewModel constructor(val routeUseCase: RouteUseCase) : AbstractViewModel() {

    override val pending = AtomicBoolean(false)

    override fun getRoutesFromServer(refresh: Boolean) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listScrolled() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val serverError = MutableLiveData<ErrorResponseEntity>()

    var pendingRoutes = MutableLiveData<Resource<RouteResponse>>()

    val routes: LiveData<PagedList<RouteEntity>> = Transformations.switchMap(
        pendingRoutes
    ) {
        it.data?.results
    }

    fun getRoutesFromCacheByPending() {
        pendingRoutes.postValue(routeUseCase.getRoutesFromCacheByPending())
    }

    fun updateRouteOnServer(
        isOnline: Boolean,
        routeEntity: RouteEntity,
        route: RouteUpdaterEntity,
        id: String?
    ) {
        scope.launch {
            if (pending.compareAndSet(false, true)) {
                routeUseCase.updateRouteOnServer(
                    isOnline = isOnline,
                    routeEntity = routeEntity,
                    route = route,
                    id = id
                ) {
                    serverError.postValue(it)
                }
            }
        }

    }

    fun clearDb() {
        routeUseCase.clearLocalDb()
    }

}


