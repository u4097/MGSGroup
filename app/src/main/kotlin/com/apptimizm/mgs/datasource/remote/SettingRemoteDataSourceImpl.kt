package com.apptimizm.mgs.datasource.remote

import com.apptimizm.mgs.data.datasource.SettingRemoteDataSource
import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.SettingEntity
import com.apptimizm.mgs.networking.SettingApi

class SettingRemoteDataSourceImpl constructor(
    private val api: SettingApi
) : SettingRemoteDataSource, BaseRepository() {

    override suspend fun get(): Resource<SettingEntity>? {
        val response = safeApiCall(
            call = { api.getPhoneAsync().await() },
            errorMessage = "Error Auth user"
        )
        return response
    }

}