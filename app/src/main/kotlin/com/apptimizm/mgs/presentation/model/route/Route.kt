package com.apptimizm.mgs.presentation.model.route

data class Route(
    val id: String? = "0",
    val address: String? = "",
    val bugs: List<Bug?>? = listOf(),
    val counterparty: String? = "",
    val costByOne: String? = "0",
    val contractNumber: String? = "",
    val isNightShift: Boolean? = false,
    val county: String? = "",
    val area: String? = "",
    val street: String? = "",
    val status: String? = "",
    val note: String? = "",
    val coordinates: String? = "",
    val contact: String? = "",
    val talon: Boolean? = false,
    val exportOnScheduleDate: String? = "",
    val getOutExportTimeStart: String? = "",
    val getOutExportTimeEnd: String? = "",
    val factOnExportDatetime: String? = "",
    val executor: String? = "",
    val schedule: String? = "0"
)