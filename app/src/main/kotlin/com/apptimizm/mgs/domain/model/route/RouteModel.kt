package com.apptimizm.mgs.domain.model.route

import com.apptimizm.mgs.presentation.model.route.Route

data class RouteModel(
    val id: String? = "0",
    val address: String? = "",
    val bugs: List<BugModel>? = listOf(),
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
    val factOnExportDatetime: Any? = Any(),
    val executor: String? = "",
    val schedule: String? = "0"
)

fun RouteModel.mapToPresentation(): Route = Route (
    id = this.id,
    address = this.address,
    bugs = this.bugs?.mapToPresentation(),
    counterparty = this.counterparty,
    costByOne = this.costByOne,
    contractNumber = this.contractNumber,
    isNightShift = this.isNightShift,
    county = this.county,
    area = this.area,
    street = this.street,
    status = this.status,
    note = this.note,
    coordinates = this.coordinates,
    contact = this.contact,
    talon = this.talon,
    exportOnScheduleDate = this.exportOnScheduleDate,
    getOutExportTimeStart = this.getOutExportTimeStart,
    getOutExportTimeEnd = this.getOutExportTimeEnd,
    factOnExportDatetime = this.factOnExportDatetime,
    executor = this.executor,
    schedule = this.schedule

)