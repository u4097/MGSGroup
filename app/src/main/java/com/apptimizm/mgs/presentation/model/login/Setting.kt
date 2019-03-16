package com.apptimizm.mgs.presentation.model.login

import com.apptimizm.mgs.domain.model.SettingModel

data class Setting(
    val setting: String?
)


fun Setting.mapToDomain(): SettingModel = SettingModel(
    setting = this.setting
)