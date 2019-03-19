package com.apptimizm.mgs.networking

import com.apptimizm.mgs.datasource.model.LoginEntity
import com.apptimizm.mgs.datasource.model.LoginResponseEntity
import com.apptimizm.mgs.datasource.model.SettingEntity
import com.apptimizm.mgs.datasource.model.route.RouteResponseEntity
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-16
 * Time: 08:00
 */

// Логин пользователя
interface LoginApi {

    @POST("auth/login/")
    fun loginAsync(
        @Body loginEntity: LoginEntity
    ): Deferred<Response<LoginResponseEntity>>
}

// Телефон диспетчерской
interface SettingApi {

    @GET("settings/")
    fun getPhoneAsync(
    ): Deferred<Response<SettingEntity>>
}

// Список маршрутов на текущую дату
interface RouteApi {

    @GET("routing_sheet/")
    fun getRouteAsync(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Deferred<Response<RouteResponseEntity>>
}
