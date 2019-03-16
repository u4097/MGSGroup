package com.apptimizm.mgs.domain.model.route

data class RouteResponseModel(
    val count: String? = "0",
    val next: String? = "",
    val previous: String? = "",
    val results: List<RouteModel>? = listOf()
)