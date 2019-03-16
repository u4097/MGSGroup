package com.apptimizm.mgs.domain.usecases

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.SettingEntity
import com.apptimizm.mgs.domain.repository.SettingRepository


class SettingUseCase constructor(private val settingRepository: SettingRepository) {

    suspend fun get(): Resource<SettingEntity>? =
        settingRepository.get()
}
