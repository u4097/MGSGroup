package com.apptimizm.mgs.datasource.model.route

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class RouteEntity(
    @field:Json(name = "id")
    val id: String? = "",
    @field:Json(name = "address")
    val address: String? = "",
    @field:Json(name = "bugs")
    val bugs: List<BugEntity> = listOf(),
    @field:Json(name = "counterparty")
    val counterparty: String? = "",
    @field:Json(name = "cost_by_one")
    val costByOne: String? = "0",
    @field:Json(name = "contract_number")
    val contractNumber: String? = "",
    @field:Json(name = "is_night_shift")
    val isNightShift: Boolean? = false,
    @field:Json(name = "county")
    val county: String? = "",
    @field:Json(name = "area")
    val area: String? = "",
    @field:Json(name = "street")
    val street: String? = "",
    @field:Json(name = "status")
    val status: String? = "",
    @field:Json(name = "note")
    val note: String? = "",
    @field:Json(name = "coordinates")
    val coordinates: String? = "",
    @field:Json(name = "contact")
    val contact: String? = "",
    @field:Json(name = "talon")
    val talon: Boolean? = false,
    @field:Json(name = "export_on_schedule_date")
    val exportOnScheduleDate: String? = "",
    @field:Json(name = "get_out_export_time_start")
    val getOutExportTimeStart: String? = "",
    @field:Json(name = "get_out_export_time_end")
    val getOutExportTimeEnd: String? = "",
    @field:Json(name = "fact_on_export_datetime")
    val factOnExportDatetime: String? = "",
    @field:Json(name = "executor")
    val executor: String? = "",
    @field:Json(name = "schedule")
    val schedule: String? = "0"
)