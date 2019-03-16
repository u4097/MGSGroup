package com.apptimizm.mgs.datasource.remote

import com.apptimizm.mgs.data.datasource.LoginRemoteDataSource
import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.LoginEntity
import com.apptimizm.mgs.datasource.model.LoginResponseEntity
import com.apptimizm.mgs.networking.LoginApi

class LoginRemoteDataSourceImpl constructor(
    private val api: LoginApi
) : LoginRemoteDataSource, BaseRepository() {

    override suspend fun get(loginEntity: LoginEntity): Resource<LoginResponseEntity>? {
        val response = safeApiCall(
            call = { api.loginAsync(loginEntity).await() },
            errorMessage = "Error Auth user"
        )
        return response
    }

}