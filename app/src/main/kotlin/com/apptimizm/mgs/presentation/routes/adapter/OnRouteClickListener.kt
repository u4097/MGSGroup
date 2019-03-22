package com.apptimizm.mgs.presentation.routes.adapter


import com.apptimizm.mgs.datasource.model.route.RouteEntity
/**
 * Created by Sitnikov Oleg
 * Date: 2019-03-22
 * Time: 23:03
 */


interface OnRouteClickListener {

    fun onRouteItemClicked(route: RouteEntity)
}
