package com.apptimizm.mgs.data.repository

import com.apptimizm.mgs.App
import com.apptimizm.mgs.data.datasource.LoginCacheDataSource
import com.apptimizm.mgs.data.datasource.LoginRemoteDataSource
import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.data.repository.resouces.ResourceState
import com.apptimizm.mgs.datasource.model.ErrorResponseEntity
import com.apptimizm.mgs.domain.model.LoginModel
import com.apptimizm.mgs.domain.repository.LoginRepository
import com.apptimizm.mgs.datasource.model.LoginResponseEntity
import com.apptimizm.mgs.datasource.model.route.RouteResponseEntity
import com.apptimizm.mgs.domain.model.mapToDataSource
import com.apptimizm.mgs.presentation.utils.pref.PrefUtils
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import timber.log.Timber

class LoginRepositoryImpl constructor(
    private val cacheDataSource: LoginCacheDataSource,
    private val remoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    private var isRequestInProgress = false

    override suspend fun get(loginModel: LoginModel): Resource<LoginResponseEntity>? =
        remoteDataSource.get(loginModel = loginModel.mapToDataSource())



    // Get from server And save routes to cache on Success.
    override suspend fun getRouteFromServer(
        refresh: Boolean,
        onSuccess: (size: Int) -> Unit,
        onError: (error: String) -> Unit
    ) {
        if (isRequestInProgress) return

        Timber.tag("ROUTE")
            .d("Get routes from server, page: 1, itemsPerPage: $NETWORK_PAGE_SIZE")
        isRequestInProgress = true

        val routes: Resource<RouteResponseEntity>? = remoteDataSource.get(1, NETWORK_PAGE_SIZE)


        routes?.data?.results?.let {
            // Save to cache
            onSuccess(it.size)
            isRequestInProgress = false
        }

        routes?.error?.let {
            onError(it)
        }
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
        private const val DATABASE_PAGE_SIZE = 20
    }


}

