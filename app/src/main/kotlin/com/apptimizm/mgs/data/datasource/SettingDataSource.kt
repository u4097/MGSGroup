package com.apptimizm.mgs.data.datasource

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.SettingEntity
import kotlinx.coroutines.Deferred


interface SettingCacheDataSource {

    fun get(): Deferred<String>

    fun set(token: String?): Deferred<String>

}

interface SettingRemoteDataSource {

    suspend fun get(): Resource<SettingEntity>?

}
