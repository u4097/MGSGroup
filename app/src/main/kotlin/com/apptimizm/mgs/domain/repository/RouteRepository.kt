package com.apptimizm.mgs.domain.repository

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.domain.model.route.RouteResponseModel

interface RouteRepository {
    suspend fun get(page: String, pageSize: String): Resource<RouteResponseModel>?
}
