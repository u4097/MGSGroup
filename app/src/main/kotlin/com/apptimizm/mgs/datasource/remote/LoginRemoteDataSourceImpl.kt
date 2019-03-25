package com.apptimizm.mgs.datasource.remote

import com.apptimizm.mgs.data.datasource.LoginRemoteDataSource
import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.LoginEntity
import com.apptimizm.mgs.datasource.model.LoginResponseEntity
import com.apptimizm.mgs.datasource.model.route.RouteResponseEntity
import com.apptimizm.mgs.networking.LoginApi

class LoginRemoteDataSourceImpl constructor(
    private val api: LoginApi
) : LoginRemoteDataSource, BaseRepository() {

    /** Get routes list from server */
    override suspend fun get(page: Int, pageSize: Int): Resource<RouteResponseEntity>? {
        return safeApiCall(
            call = { api.getRouteAsync(page, pageSize).await() },
            errorMessage = "Failure get routes from server!"
        )
    }


    override suspend fun get(loginEntity: LoginEntity): Resource<LoginResponseEntity>? {
        return safeApiCall(
        call = { api.loginAsync(loginEntity).await() },
        errorMessage = "Error Auth user"
    )
    }

}