package com.apptimizm.mgs.domain.repository

import androidx.lifecycle.LiveData
import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.ErrorResponseEntity
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.domain.model.route.RouteResponse

interface RouteRepository {

    fun getRouteFromCache(): Resource<RouteResponse>

    fun getRouteFromCacheById(routeId: String): LiveData<RouteEntity>

    suspend fun getRouteFromServerAndSave(refresh: Boolean, onError: (error: ErrorResponseEntity) -> Unit)
}
