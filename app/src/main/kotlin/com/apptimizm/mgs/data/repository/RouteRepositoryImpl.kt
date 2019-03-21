package com.apptimizm.mgs.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.apptimizm.mgs.data.datasource.RouteRemoteDataSource
import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.data.repository.resouces.ResourceState
import com.apptimizm.mgs.datasource.model.ErrorResponseEntity
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.datasource.model.route.RouteResponseEntity
import com.apptimizm.mgs.db.RoomLocalCache
import com.apptimizm.mgs.domain.model.route.RouteResponse
import com.apptimizm.mgs.domain.repository.RouteRepository
import com.apptimizm.mgs.presentation.utils.pref.PrefUtils
import com.squareup.moshi.JsonAdapter
import timber.log.Timber
import com.squareup.moshi.Moshi
import java.util.concurrent.atomic.AtomicBoolean


class RouteRepositoryImpl constructor(
    private val roomCache: RoomLocalCache,
    private val remoteDataSource: RouteRemoteDataSource
) : RouteRepository {

    // keep the last requested page. When the request is successful, increment the page number.
    private var nextRequestedPage = 1

    // LiveData of network errors.
    private val networkErrors = MutableLiveData<String>()

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false


    override fun getRouteFromCache(): Resource<RouteResponse> {
        var data: LiveData<PagedList<RouteEntity>>? = null
        // Get from cache first.
        // Get data source factory from room cache
        val dataSourceFactory = roomCache.get()
        // Get data from room cache
        data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE).build()

        return Resource(ResourceState.SUCCESS, RouteResponse(data, networkErrors))

    }


    // Get from server And save routes to cache on Success.
    override suspend fun getRouteFromServerAndSave(refresh: Boolean, onError: (error: ErrorResponseEntity) -> Unit) {
        val pending = AtomicBoolean(false)
        var routes: Resource<RouteResponseEntity>? = null
        if (isRequestInProgress) return

        if (refresh) {
            roomCache.delete()
            PrefUtils.nextpage = 1
            Timber.tag("ROUTE").d("Remove data from cache")
            isRequestInProgress = true
        }

        if (pending.compareAndSet(false, true)) {
            Timber.tag("ROUTE")
                .d("Get routes from server, page: ${PrefUtils.nextpage}, itemsPerPage: $NETWORK_PAGE_SIZE")
            routes = remoteDataSource.get(PrefUtils.nextpage, NETWORK_PAGE_SIZE)
            isRequestInProgress = true
        } else {
            return
        }


        routes?.data?.results?.let {
            // Save to cache
            pending.set(false)
            roomCache.insert(routes.data?.results!!) {
                PrefUtils.nextpage++
                isRequestInProgress = false
            }
        }

        routes?.message?.let {
            val moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<ErrorResponseEntity> = moshi.adapter(ErrorResponseEntity::class.java)
            val error: ErrorResponseEntity = jsonAdapter.fromJson(it)!!
            Timber.tag("ROUTE").e("Failure to get routes: \n ${error.errors}")

            onError(error)
        }
    }


    companion object {
        private const val NETWORK_PAGE_SIZE = 50
        private const val DATABASE_PAGE_SIZE = 20
    }

}


