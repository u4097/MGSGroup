package com.apptimizm.mgs.domain.usecases

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.domain.model.route.RouteModel
import com.apptimizm.mgs.domain.model.route.RouteResponseModel
import com.apptimizm.mgs.domain.repository.RouteRepository


class RouteUseCase constructor(private val routeRepository: RouteRepository) {

    suspend fun get(page: String, pageSize: String): Resource<RouteResponseModel>? =
        routeRepository.get(page, pageSize)

}
