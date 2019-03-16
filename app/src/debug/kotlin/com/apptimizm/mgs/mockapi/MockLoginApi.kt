package com.apptimizm.mgs.mockapi

import com.apptimizm.mgs.datasource.model.LoginEntity
import com.apptimizm.mgs.datasource.model.LoginResponseEntity
import com.apptimizm.mgs.networking.LoginApi
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit

class MockLoginApi(mockRetrofit: MockRetrofit) : LoginApi {

    private val delegate: BehaviorDelegate<LoginApi> =
        mockRetrofit.create(LoginApi::class.java)

    override fun loginAsync(loginEntity: LoginEntity): Deferred<Response<LoginResponseEntity>> {
        val response = LoginResponseEntity(token)
        return delegate.returningResponse(response).loginAsync(loginEntity)
    }

    private val token =
        "!~ Mocking Token ~!"

}
