package com.apptimizm.mgs.datasource.model.route

import com.squareup.moshi.Json

data class RouteUpdaterEntity(

    @field:Json(name = "datetime_get_out")
    val dateTimeGetOut: String,

    @field:Json(name = "talon")
    val talon: Boolean?,

    @field:Json(name = "bugs")
    val bugs: List<BugEntity>

)

