package com.apptimizm.mgs.datasource.model.route

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.domain.model.route.RouteModel
import com.apptimizm.mgs.domain.model.route.RouteResponseModel
import com.squareup.moshi.Json

data class RouteResponseEntity(
    @field:Json(name = "count")
    val count: String? = "0",
    @field:Json(name = "next")
    val next: String? = "",
    @field:Json(name = "previous")
    val previous: String? = "",
    @field:Json(name = "results")
    val results: List<RouteEntity>? = listOf()
)

fun RouteEntity.mapToDomain(): RouteModel =
    RouteModel(
        id = this.id,
        address = this.address,
        bugs = this.bugs?.mapToDomain(),
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

fun List<RouteEntity>.mapToDomain(): List<RouteModel> = map { it.mapToDomain() }

fun RouteResponseEntity.mapToDomain(): RouteResponseModel = RouteResponseModel(

    count = this.count,
    next = this.next,
    previous = this.previous,
    results = this.results?.mapToDomain()
)


fun Resource<RouteResponseEntity>.mapToDomain(): Resource<RouteResponseModel> = Resource<RouteResponseModel>(
    state = state,
    data = data?.mapToDomain(),
    error = error
)

