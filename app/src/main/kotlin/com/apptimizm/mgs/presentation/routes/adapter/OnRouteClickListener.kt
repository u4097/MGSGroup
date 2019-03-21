package com.apptimizm.mgs.presentation.routes.adapter


import com.apptimizm.mgs.datasource.model.route.RouteEntity

interface OnRouteClickListener {

    fun onRouteItemClicked(route: RouteEntity)
}
