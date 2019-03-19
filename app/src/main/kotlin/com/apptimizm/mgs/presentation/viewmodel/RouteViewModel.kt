package com.apptimizm.mgs.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.domain.model.route.RouteResponse
import com.apptimizm.mgs.domain.usecases.RouteUseCase
import com.apptimizm.mgs.presentation.utils.livedata.SingleLiveEvent
import kotlinx.coroutines.launch

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-16
 * Time: 09:22
 */


class RouteViewModel constructor(val routeUseCase: RouteUseCase) : AbstractViewModel() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    val refreshRouteList = SingleLiveEvent<Boolean>()

    private val routeResult: LiveData<RouteResponse> = Transformations.map(refreshRouteList) {
        routeUseCase.getRoutesFromCache()
    }

    val routes: LiveData<PagedList<RouteEntity>> = Transformations.switchMap(
        routeResult
    ) { it.results }

    val networkErrors: LiveData<String> = Transformations.switchMap(
        routeResult
    ) { it.networkErrors }


    /**
     * Refresh routes from  repository based.
     */
    fun getRoutes(isRefresh: Boolean) {
        if (isRefresh)
            refreshRouteList.postValue(true)
    }

    fun listScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_THRESHOLD >= totalItemCount) {
            scope.launch {
                routeUseCase.getRouteFromServer()
            }
        }
    }
}


//    val networkError = SingleLiveEvent<String>()

//fun getRoutes(page: String, pageSize: String) {
//    scope.launch {
/*             routeUseCase.getRouteFromServerAndSave(page, pageSize, { routes.postValue(it) }, {
                if (!it.isEmpty())
                    networkError.postValue(it)
            })*/
//    }
//}

//}
