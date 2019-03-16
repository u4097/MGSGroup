package com.apptimizm.mgs.data.repository

import com.apptimizm.mgs.data.datasource.RouteCacheDataSource
import com.apptimizm.mgs.data.datasource.RouteRemoteDataSource
import com.apptimizm.mgs.data.repository.resouces.Resource
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
/*        when (refresh) {
            true -> {
                val routes = remoteDataSource.get()
                // Save to cache
                cacheDataSource.set(routes = routes?.data!!)
                return routes.mapToDomain()
            }
        }*/
/*        var routeList: RouteResponseEntity?
        // Get from cache
        var response = cacheDataSource.get()
        response.await().let {
            routeList = it
        }
        routeList?.let {
            if (!routeList?.results?.isEmpty()!!) {
                Timber.d("Get route from cache")
                return Resource(ResourceState.SUCCESS, routeList?.mapToDomain())
            }
        }*/

        val routes = remoteDataSource.get(page, pageSize)
        Timber.d("Get route from server:\n ${routes?.data?.results}")
        routes?.data?.results?.let {
            try {
                // Save to cache
                cacheDataSource.set(routes = routes.data)
            } catch (e: Exception) {
                Timber.e("Can't save route into cache: ${e.stackTrace}")
            }
        }
        return routes?.mapToDomain()
    }

}


