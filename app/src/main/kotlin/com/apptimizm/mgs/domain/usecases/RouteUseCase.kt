package com.apptimizm.mgs.domain.usecases

import com.apptimizm.mgs.domain.repository.RouteRepository


class RouteUseCase constructor(private val routeRepository: RouteRepository) {

    suspend fun getRouteFromServer() =
        routeRepository.getRouteFromServerAndSave()

    fun getRoutesFromCache() =
            routeRepository.getRouteFromCache()
}
