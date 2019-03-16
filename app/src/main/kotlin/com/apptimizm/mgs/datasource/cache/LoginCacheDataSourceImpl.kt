package com.apptimizm.mgs.datasource.cache

import com.apptimizm.mgs.cache.AppCache
import com.apptimizm.mgs.data.datasource.LoginCacheDataSource
import kotlinx.coroutines.Deferred


class LoginCacheDataSourceImpl constructor(
    private val cache: AppCache<String>
) : LoginCacheDataSource {
    val key = "token"

    override fun get(): Deferred<String> =
        cache.load(key)

    override fun set(token: String?): Deferred<String> =
        cache.save(key, token)

}
