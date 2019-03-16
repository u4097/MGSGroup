package com.apptimizm.mgs.presentation.model.route


data class RouteResponse(
    val count: String? = "0",
    val next: String? = "",
    val previous: String? = "",
    val results: List<Route?>? = listOf()
)