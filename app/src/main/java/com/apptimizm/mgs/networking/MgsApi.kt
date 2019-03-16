package com.apptimizm.mgs.networking

import com.apptimizm.mgs.datasource.model.LoginEntity
import com.apptimizm.mgs.datasource.model.LoginResponseEntity
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-07
 * Time: 21:40
 */

// Логин
interface LoginApi {
    @POST("auth/login/")
    fun loginAsync(
        @Body loginEntity: LoginEntity
    ): Deferred<Response<LoginResponseEntity>>
}

