package com.apptimizm.mgs.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.apptimizm.mgs.App
import com.apptimizm.mgs.AppConfiguration.remoteDataSource
import com.apptimizm.mgs.data.datasource.RouteRemoteDataSource
import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.data.repository.resouces.ResourceState
import com.apptimizm.mgs.datasource.model.ErrorResponseEntity
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.datasource.model.route.RouteResponseEntity
import com.apptimizm.mgs.datasource.model.route.RouteUpdaterEntity
import com.apptimizm.mgs.db.RoomLocalCache
import com.apptimizm.mgs.domain.model.route.RouteResponse
import com.apptimizm.mgs.domain.repository.RouteRepository
import com.apptimizm.mgs.presentation.utils.pref.PrefUtils
import com.squareup.moshi.JsonAdapter
import timber.log.Timber
import com.squareup.moshi.Moshi


class RouteRepositoryImpl constructor(
    private val roomCache: RoomLocalCache,
    private val remoteDataSource: RouteRemoteDataSource
) : RouteRepository {

    // LiveData of network errors.
    private val networkErrors = MutableLiveData<String>()

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false
    private var isNoMorePages = false


    override fun getRouteFromCache(): Resource<RouteResponse> {
        var data: LiveData<PagedList<RouteEntity>>? = null
        // Get from cache first.
        // Get data source factory from room cache
        val dataSourceFactory = roomCache.get()
        // Get data from room cache
        data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE).build()

        return Resource(ResourceState.SUCCESS, RouteResponse(data, networkErrors))

    }

    override fun getRouteFromCacheByStatus(status: String): Resource<RouteResponse> {
        val data: LiveData<PagedList<RouteEntity>>?
        // Get from cache first.
        // Get data source factory from room cache
        val dataSourceFactory = roomCache.getRouteByStatus(status)
        // Get data from room cache
        data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE).build()

        return Resource(ResourceState.SUCCESS, RouteResponse(data, networkErrors))
    }

    override fun getRouteFromCacheActiveAndPending(): Resource<RouteResponse> {
        val data: LiveData<PagedList<RouteEntity>>?
        // Get from cache first.
        // Get data source factory from room cache
        val dataSourceFactory = roomCache.getRouteActiveAndPending()
        // Get data from room cache
        data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE).build()

        return Resource(ResourceState.SUCCESS, RouteResponse(data, networkErrors))
    }

    override fun getRouteFromCacheByPending():  Resource<RouteResponse> {
        val data: LiveData<PagedList<RouteEntity>>?
        val dataSourceFactory = roomCache.getRoutesByPending()
        data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE).build()
        return Resource(ResourceState.SUCCESS, RouteResponse(data, networkErrors))
    }


    override fun getRouteFromCacheById(routeId: String): LiveData<RouteEntity> {
        return roomCache.selectRouteById(routeId)
    }

    override suspend fun updateRouteOnServer(
        isOnline: Boolean,
        routeEntity: RouteEntity,
        route: RouteUpdaterEntity,
        id: String?,
        onError: (error: ErrorResponseEntity) -> Unit
    ) {
        if (App.instance.isOnline()) {
            Timber.tag("ROUTE").d("Update online")
            remoteDataSource.update(route, id)
        }
        Timber.tag("ROUTE").d("route: ${routeEntity.id}, route marked as pending: ${routeEntity.pending}")
        roomCache.update(routeEntity) {
            // PrefUtils.nextpage++
            //isRequestInProgress = false
        }
    }


    // Get from server And save routes to cache on Success.
    override suspend fun getRouteFromServerAndSave(
        refresh: Boolean,
        onSuccess: (size: Int) -> Unit,
        onError: (error: ErrorResponseEntity) -> Unit
    ) {
        if (isRequestInProgress) return

        if (refresh) {
            App.instance.processedPages.clear()
            roomCache.delete()
            PrefUtils.nextpage = 1
            Timber.tag("ROUTE").d("Remove data from cache")
            isRequestInProgress = true
            isNoMorePages = false
        }

        if (isNoMorePages) return
        Timber.tag("ROUTE")
            .d("Get routes from server, page: ${PrefUtils.nextpage}, itemsPerPage: $NETWORK_PAGE_SIZE")
        isRequestInProgress = true
        val routes: Resource<RouteResponseEntity>? = remoteDataSource.get(PrefUtils.nextpage, NETWORK_PAGE_SIZE)


        routes?.data?.results?.let {
            // Save to cache
            roomCache.insert(routes.data.results) {
                App.instance.processedPages.add(PrefUtils.nextpage)
                Timber.d("processed data: ${App.instance.processedPages}")
                onSuccess(it.size)
                PrefUtils.nextpage++
                isRequestInProgress = false
            }
        }

        routes?.data.let {
            if (it?.next == null) {
                Timber.d("next page: ${it?.next}. Return")
                isRequestInProgress = false
                return
            }
        }

        routes?.error?.let {
            val moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<ErrorResponseEntity> = moshi.adapter(ErrorResponseEntity::class.java)
            val error: ErrorResponseEntity = jsonAdapter.fromJson(it)!!
            Timber.tag("ROUTE").e("Failure to get routes: \n ${error.errors}")

            if (error.statusCode?.equals("404")!!) {
                isNoMorePages = true
                Timber.tag("ROUTE").d("Return from 404.")
                isRequestInProgress = false
                return
            }
            onError(error)
        }
    }


    companion object {
        private const val NETWORK_PAGE_SIZE = 50
        private const val DATABASE_PAGE_SIZE = 20
    }

}


