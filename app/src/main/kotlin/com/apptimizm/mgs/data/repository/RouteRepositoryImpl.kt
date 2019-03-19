package com.apptimizm.mgs.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import com.apptimizm.mgs.data.datasource.RouteCacheDataSource
import com.apptimizm.mgs.data.datasource.RouteRemoteDataSource
import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.data.repository.resouces.ResourceState
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.datasource.model.route.RouteResponseEntity
import com.apptimizm.mgs.datasource.model.route.mapToDomain
import com.apptimizm.mgs.db.RoomLocalCache
import com.apptimizm.mgs.domain.model.route.RouteResponse
import com.apptimizm.mgs.domain.repository.RouteRepository
import timber.log.Timber

class RouteRepositoryImpl constructor(
    private val roomCache: RoomLocalCache,
    private val remoteDataSource: RouteRemoteDataSource
) : RouteRepository {

    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = 1

    // LiveData of network errors.
    private val networkErrors = MutableLiveData<String>()

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false


    override suspend fun getRouteFromCache(page: String, pageSize: String): Resource<RouteResponse>? {
//        var routeList: RouteResponseEntity?
        // Get from cache first.
//        val response = cacheDataSource.get()
        // Get data source factory from room cache
        val dataSourceFactory = roomCache.get()
        // Get data from room cache
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE).build()

        return Resource(ResourceState.SUCCESS, RouteResponse(data, networkErrors))

/*        response.await().let {
            routeList = it
        }
        routeList?.let {
            if (!routeList?.results?.isEmpty()!!) {
                Timber.d("Get routes from cache!")
                return Resource(ResourceState.SUCCESS, routeList?.mapToDomain())
            }
        }*/

    }


    // Get from server.
    override suspend fun getRouteFromServer(
        page: String,
        pageSize: String,
        onSuccess: (routes: List<RouteEntity>) -> Unit,
        onError: (error: String) -> Unit
    ) {
        if(isRequestInProgress) return

        val routes = remoteDataSource.get(page, pageSize)
        Timber.d("Get routes from server, page: $page, itemsPerPage: $pageSize")

        isRequestInProgress = true

        routes?.data?.results?.let {
            onSuccess(routes.data.results)
            roomCache.insert(routes.data.results) {
                lastRequestedPage ++
                isRequestInProgress = false
            }
        }

        routes?.message?.let {
            onError(it)
        }
    }


/*        routes?.data?.results?.let {
            try {
                // Save to cache
                cacheDataSource.set(routes = routes.data)
            } catch (e: Exception) {
                Timber.e("Can't save routes into cache:\n ${e.stackTrace}")
            }*/
//        }
//    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
        private const val DATABASE_PAGE_SIZE = 10
    }

}


