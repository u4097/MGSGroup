package com.apptimizm.mgs.datasource.cache

import com.apptimizm.mgs.cache.AppCache
import com.apptimizm.mgs.data.datasource.RouteCacheDataSource
import com.apptimizm.mgs.datasource.model.route.RouteResponseEntity
import kotlinx.coroutines.Deferred


class RouteCacheDataSourceImpl constructor(
    private val cache: AppCache<RouteResponseEntity>
) : RouteCacheDataSource {
    val key = "route"

    override fun set(routes: RouteResponseEntity): Deferred<RouteResponseEntity> {
        return cache.save(key, routes)
    }

    override fun get(): Deferred<RouteResponseEntity> =
        cache.load(key)
}
