package com.apptimizm.mgs.mockapi

import com.apptimizm.mgs.datasource.model.LoginEntity
import com.apptimizm.mgs.datasource.model.LoginResponseEntity
import com.apptimizm.mgs.datasource.model.route.RouteResponseEntity
import com.apptimizm.mgs.networking.LoginApi
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit

class MockLoginApi(mockRetrofit: MockRetrofit) : LoginApi {

    override fun getRouteAsync(page: Int, pageSize: Int): Deferred<Response<RouteResponseEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val delegate: BehaviorDelegate<LoginApi> =
        mockRetrofit.create(LoginApi::class.java)

    override fun loginAsync(loginEntity: LoginEntity): Deferred<Response<LoginResponseEntity>> {
        val response = LoginResponseEntity(token)
        return delegate.returningResponse(response).loginAsync(loginEntity)
    }

    private val token =
        "\uD83D\uDE28 !~ This is Mocking Token ~!"

}
