package com.apptimizm.mgs.datasource.remote

import com.apptimizm.mgs.data.datasource.RouteRemoteDataSource
import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.route.RouteResponseEntity
import com.apptimizm.mgs.datasource.model.route.RouteUpdaterEntity
import com.apptimizm.mgs.networking.RouteApi

class RouteRemoteDataSourceImpl constructor(
    private val api: RouteApi
) : RouteRemoteDataSource, BaseRepository() {


    /** Finish route */
    override suspend fun update(
        route: RouteUpdaterEntity,
        id: String?
    ): Resource<Unit>? {
        return safeApiCall(
            call = { api.updateRouteAsync(route, id!!).await() },
            errorMessage = "Failure update routes on server!"
        )
    }


    /** Get routes list from server */
    override suspend fun get(page: Int, pageSize: Int): Resource<RouteResponseEntity>? {
        return safeApiCall(
            call = { api.getRouteAsync(page, pageSize).await() },
            errorMessage = "Failure get routes from server!"
        )
    }


}