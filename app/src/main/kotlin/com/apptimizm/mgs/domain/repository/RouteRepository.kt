package com.apptimizm.mgs.domain.repository

import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.domain.model.route.RouteResponse

interface RouteRepository {

    fun getRouteFromCache(): RouteResponse

    suspend fun getRouteFromServerAndSave()
}
