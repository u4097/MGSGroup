package com.apptimizm.mgs.datasource.cache

import com.apptimizm.mgs.cache.AppCache
import com.apptimizm.mgs.data.datasource.SettingCacheDataSource
import kotlinx.coroutines.Deferred


class SettingCacheDataSourceImpl constructor(
    private val cache: AppCache<String>
) : SettingCacheDataSource {
    val key = "phone"

    override fun get(): Deferred<String> =
        cache.load(key)

    override fun set(token: String?): Deferred<String> =
        cache.save(key, token)

}
