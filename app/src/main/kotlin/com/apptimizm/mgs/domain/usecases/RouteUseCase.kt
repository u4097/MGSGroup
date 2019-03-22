package com.apptimizm.mgs.domain.usecases

import androidx.lifecycle.LiveData
import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.ErrorResponseEntity
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.domain.model.route.RouteResponse
import com.apptimizm.mgs.domain.repository.RouteRepository


class RouteUseCase constructor(private val routeRepository: RouteRepository) {

    suspend fun getRouteFromServer(refresh: Boolean, onError: (error: ErrorResponseEntity) -> Unit) =
        routeRepository.getRouteFromServerAndSave(refresh = refresh, onError = onError)

    fun getRoutesFromCache(): Resource<RouteResponse> =
        routeRepository.getRouteFromCache()

    fun getRouteFromCacheById(routeId: String): LiveData<RouteEntity> =
        routeRepository.getRouteFromCacheById(routeId)
}
