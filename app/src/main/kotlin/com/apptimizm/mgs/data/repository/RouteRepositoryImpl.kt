package com.apptimizm.mgs.data.repository

import com.apptimizm.mgs.data.datasource.RouteCacheDataSource
import com.apptimizm.mgs.data.datasource.RouteRemoteDataSource
import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.data.repository.resouces.ResourceState
import com.apptimizm.mgs.datasource.model.route.RouteResponseEntity
import com.apptimizm.mgs.datasource.model.route.mapToDomain
import com.apptimizm.mgs.domain.model.route.RouteResponseModel
import com.apptimizm.mgs.domain.repository.RouteRepository
import timber.log.Timber
import java.lang.Exception

class RouteRepositoryImpl constructor(
    private val cacheDataSource: RouteCacheDataSource,
    private val remoteDataSource: RouteRemoteDataSource
) : RouteRepository {

    override suspend fun get(page: String, pageSize: String): Resource<RouteResponseModel>? {
        var routeList: RouteResponseEntity?
        // Get from cache first.
        val response = cacheDataSource.get()
        response.await().let {
            routeList = it
        }
        routeList?.let {
            if (!routeList?.results?.isEmpty()!!) {
                Timber.d("Get routes from cache!")
                return Resource(ResourceState.SUCCESS, routeList?.mapToDomain())
            }
        }

        // Get from server.
        val routes = remoteDataSource.get(page, pageSize)
        Timber.d("Get routes from server:\n ${routes?.data?.results}")
        routes?.data?.results?.let {
            try {
                // Save to cache
                cacheDataSource.set(routes = routes.data)
            } catch (e: Exception) {
                Timber.e("Can't save routes into cache:\n ${e.stackTrace}")
            }
        }
        return routes?.mapToDomain()
    }

}


