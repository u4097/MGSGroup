package com.apptimizm.mgs.networking

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.datasource.model.route.RouteUpdaterEntity
import com.apptimizm.mgs.domain.usecases.RouteUseCase
import timber.log.Timber

interface OnUpdateListener {
    fun onUpdate()
}

class UpdateReceiver(val routeUseCase: RouteUseCase, val onUpdateListener: OnUpdateListener) : BroadcastReceiver() {
    var mContext: Context? = null

    var onUpdateReceiver: OnUpdateListener? = null

    override fun onReceive(context: Context, intent: Intent) {
        onUpdateReceiver = onUpdateListener
        mContext = context
        val conn = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = conn.activeNetworkInfo

        if (networkInfo != null) {
            if (firstConnect) {
                firstConnect = false
                Timber.tag("ROUTE").i("Is online now!")
                // TODO: 21/09/2018 update db here...
//                update()
                onUpdateReceiver?.onUpdate()

            } else {
                firstConnect = true
            }
        } else {
            // TODO: 21/09/2018 Show here msg NoInetConnection dialog, and remove this dialog from another places.
        }
    }


    private fun update() {
        // Get all not pending routes from cache
        val pendingRoutes = routeUseCase.getRoutesFromCacheByPending()


//        Timber.tag("ROUTE").d("Pending routes size: ${pendingRoutes.data?.value?.size}")
/*
        dataManager.getPendingRoutesFromDb()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { route ->
                    val routeUpdater = getRouteUpdter(route)
                    disposables.add(dataManager.updateRoute(routeUpdater, route.id())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            { updateResponse ->
                                //                        Timber.d("Route successfully update, %s", updateResponse.dateTimeGetOut());
                                markRouteAsUpdated(route)
                            },
                            { throwable ->
                                //                        Timber.d("Some errors: %s", throwable.getCause());
                            }
                        )
                    )
                },
                { throwable ->
                    //              Timber.d("Some errors: %s", throwable.getCause());
                }
            )
*/


    }

    private fun markRouteAsUpdated(mRoute: RouteEntity) {
/*
        val routeToUpdate = Route.builder()
            .setId(mRoute.id())
            .setContragent(mRoute.contragent())
            .setAddress(mRoute.address())
            .setContact(mRoute.contact())
            .setCoordinates(mRoute.coordinates())
            .setExecutor(mRoute.executor())
            .setDateRemovalPlan(mRoute.dateRemovalPlan())
            .setTimeRemovalStart(mRoute.timeRemovalStart())
            .setTimeRemovalEnd(mRoute.timeRemovalEnd())
            .setBugs(mRoute.bugs())
            .setStatus("active")
            .setDateRemovalFact(getCurrentDateTime())
            .setTalon(mRoute.talon())
            .setUpdated(true)
            .build()

        disposables.add(dataManager.updateLocalRoute(routeToUpdate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { routesToSave ->
                    //              Timber.d("Routes successfully marked as not updates in db.");
                },

                { throwable ->
                    //              Timber.d("Some errors: %s", throwable.getCause());
                }
            )
        )
*/

    }

    private fun getRouteUpdter(route: RouteEntity): RouteUpdaterEntity {
/*        return RouteUpdater.builder()
            .setBugs(route.bugs())
            .setDateTimeGetOut(route.dateRemovalFact())

            .setTalon(route.talon())
            .build()*/
        return RouteUpdaterEntity("", false, route.bugs!!)
    }

    companion object {
        private var firstConnect = true
    }


}
