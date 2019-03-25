package com.apptimizm.mgs.data.datasource

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.LoginEntity
import com.apptimizm.mgs.datasource.model.LoginResponseEntity
import com.apptimizm.mgs.datasource.model.route.RouteResponseEntity
import kotlinx.coroutines.Deferred


interface LoginCacheDataSource {

    fun get(): Deferred<String>

    fun set(token: String?): Deferred<String>

}

interface LoginRemoteDataSource {

    suspend fun get(loginModel: LoginEntity): Resource<LoginResponseEntity>?

    suspend fun get(page: Int, pageSize: Int): Resource<RouteResponseEntity>?
}
