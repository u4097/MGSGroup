package com.apptimizm.mgs.data.repository

import com.apptimizm.mgs.data.datasource.SettingCacheDataSource
import com.apptimizm.mgs.data.datasource.SettingRemoteDataSource
import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.data.repository.resouces.ResourceState
import com.apptimizm.mgs.datasource.model.SettingEntity
import com.apptimizm.mgs.domain.repository.SettingRepository
import com.apptimizm.mgs.domain.model.SettingModel

class SettingRepositoryImpl constructor(
    private val cacheDataSource: SettingCacheDataSource,
    private val remoteDataSource: SettingRemoteDataSource
) : SettingRepository {


        override suspend fun get(): Resource<SettingEntity>? {
            val settingResponse = remoteDataSource.get()
            cacheDataSource.set(settingResponse?.data?.setting)
            return settingResponse
        }

    }

