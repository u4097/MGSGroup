package com.apptimizm.mgs.datasource.model

import com.apptimizm.mgs.domain.model.SettingModel
import com.squareup.moshi.Json

class SettingEntity(

    @field:Json(name = "phone")
    val setting: String?

)

fun SettingEntity.mapToDomain(): SettingModel =
    SettingModel(
        setting = this.setting
    )
