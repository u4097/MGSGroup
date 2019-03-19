package com.apptimizm.mgs.domain.usecases

import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.domain.repository.RouteRepository


class RouteUseCase constructor(private val routeRepository: RouteRepository) {

    suspend fun getRouteFromServer(
        page: String,
        pageSize: String,
        onSuccess: (routesResources: List<RouteEntity>) -> Unit,
        onError: (error: String) -> Unit
    ) =
        routeRepository.getRouteFromServer(
            page,
            pageSize,
            {it},
            {it}
            )

}
