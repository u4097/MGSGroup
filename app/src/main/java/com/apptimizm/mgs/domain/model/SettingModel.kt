package com.apptimizm.mgs.domain.model

import com.apptimizm.mgs.datasource.model.SettingEntity

data class SettingModel(
    val setting: String? = "NoName"
)


fun SettingModel.mapToDataSource(): SettingEntity =
    SettingEntity(
        setting = this.setting
    )