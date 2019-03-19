package com.apptimizm.mgs.data.datasource

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.route.RouteResponseEntity
import kotlinx.coroutines.Deferred


interface RouteCacheDataSource {

    fun get(): Deferred<RouteResponseEntity>

    fun set(routes: RouteResponseEntity): Deferred<RouteResponseEntity>

}

interface RouteRemoteDataSource {

    suspend fun get(page: Int, pageSize: Int): Resource<RouteResponseEntity>?

}
