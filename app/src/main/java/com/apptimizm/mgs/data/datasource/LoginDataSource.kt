package com.apptimizm.mgs.data.datasource

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.LoginEntity
import com.apptimizm.mgs.datasource.model.LoginResponseEntity
import kotlinx.coroutines.Deferred


interface LoginCacheDataSource {

    fun get(): Deferred<String>

    fun set(token: String?): Deferred<String>

}

interface LoginRemoteDataSource {

    suspend fun get(loginModel: LoginEntity): Resource<LoginResponseEntity>?

}
