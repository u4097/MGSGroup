package com.apptimizm.mgs.domain.repository

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.SettingEntity

interface SettingRepository {

    suspend fun get(): Resource<SettingEntity>?
}
