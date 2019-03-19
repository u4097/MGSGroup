package com.apptimizm.mgs.datasource.remote

import com.apptimizm.mgs.data.datasource.RouteRemoteDataSource
import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.route.RouteResponseEntity
import com.apptimizm.mgs.networking.RouteApi

class RouteRemoteDataSourceImpl constructor(
    private val api: RouteApi
) : RouteRemoteDataSource, BaseRepository() {


    override suspend fun get(page: Int, pageSize: Int): Resource<RouteResponseEntity>? {
        val response = safeApiCall(
            call = { api.getRouteAsync(page, pageSize).await() },
            errorMessage = "Failure get routes from server!"
        )
        return response
    }


}