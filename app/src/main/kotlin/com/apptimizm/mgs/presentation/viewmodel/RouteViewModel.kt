package com.apptimizm.mgs.presentation.viewmodel

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.domain.model.route.RouteResponseModel
import com.apptimizm.mgs.domain.usecases.RouteUseCase
import com.apptimizm.mgs.presentation.utils.livedata.SingleLiveEvent
import kotlinx.coroutines.*

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-16
 * Time: 09:22
 */


class RouteViewModel constructor(val routeUseCase: RouteUseCase) : AbstractViewModel() {

    val routes = SingleLiveEvent<Resource<RouteResponseModel>>()

    fun getRoutes(page: String, pageSize: String) {
        scope.launch {
            val response = routeUseCase.get(page, pageSize)
            routes.postValue(response)
        }
    }

}