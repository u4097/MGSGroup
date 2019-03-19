package com.apptimizm.mgs.domain.model.route

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.apptimizm.mgs.datasource.model.route.RouteEntity

data class RouteResponse(
    val results: LiveData<PagedList<RouteEntity>>,
    val networkErrors: LiveData<String>
)