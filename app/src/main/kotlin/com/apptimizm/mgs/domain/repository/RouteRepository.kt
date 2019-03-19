package com.apptimizm.mgs.domain.repository

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.domain.model.route.RouteResponse

interface RouteRepository {
    suspend fun getRouteFromCache(page: String, pageSize: String): Resource<RouteResponse>?

    suspend fun getRouteFromServer(page: String,
                                   pageSize: String,
                                   onSuccess:(routes: List<RouteEntity>)->Unit,
                                   onError: (error: String)->Unit)
}
