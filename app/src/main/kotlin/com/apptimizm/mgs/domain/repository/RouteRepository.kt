package com.apptimizm.mgs.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.ErrorResponseEntity
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.datasource.model.route.RouteUpdaterEntity
import com.apptimizm.mgs.domain.model.route.RouteResponse

interface RouteRepository {

    fun getRouteFromCache(): Resource<RouteResponse>

    fun getRouteFromCacheByStatus(status: String): Resource<RouteResponse>

    fun getRouteFromCacheActiveAndPending(): Resource<RouteResponse>

    fun getRouteFromCacheByPending(): Resource<RouteResponse>

    fun getRouteFromCacheById(routeId: String): LiveData<RouteEntity>

    fun clearDb()

    suspend fun getRouteFromServerAndSave(refresh: Boolean, onSuccess:(size: Int)->Unit, onError: (error: ErrorResponseEntity) -> Unit)

    suspend fun updateRouteOnServer(isOnline: Boolean, routeEntity: RouteEntity, route: RouteUpdaterEntity, id: String?, onError: (error: ErrorResponseEntity) -> Unit)
}
