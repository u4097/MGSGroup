package com.apptimizm.mgs.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import com.apptimizm.mgs.data.datasource.RouteRemoteDataSource
import com.apptimizm.mgs.datasource.model.route.RouteEntity
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


    override  fun getRouteFromCache(): RouteResponse {
        // Get from cache first.
        // Get data source factory from room cache
        val dataSourceFactory = roomCache.get()
        // Get data from room cache
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE).build()

        return RouteResponse(data, networkErrors)

    }


    // Get from server And save routes to cache on Success.
    override suspend fun getRouteFromServerAndSave() {
        if(isRequestInProgress) return

        val routes = remoteDataSource.get(lastRequestedPage, NETWORK_PAGE_SIZE)
        Timber.d("Get routes from server, page: $lastRequestedPage, itemsPerPage: $NETWORK_PAGE_SIZE")

        isRequestInProgress = true

        routes?.data?.results?.let {
//            onSuccess(routes.data.results)
            // Save to cache
            roomCache.insert(routes.data.results) {
                lastRequestedPage ++
                isRequestInProgress = false
            }
        }

        routes?.message?.let {
            Timber.e("Falure get routes: \n $it")
        }
    }


    companion object {
        private const val NETWORK_PAGE_SIZE = 20
        private const val DATABASE_PAGE_SIZE = 10
    }

}


