/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.apptimizm.mgs.db

import androidx.paging.DataSource
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import timber.log.Timber
import java.util.concurrent.Executor

/**
 * Class that handles the DAO local data source. This ensures that methods are triggered on the
 * correct executor.
 */
class RoomLocalCache(
        private val routeDao: RouteDao,
        private val ioExecutor: Executor
) {

    /**
     * Insert a list of routes in the database, on a background thread.
     */
    fun insert(routes: List<RouteEntity>, insertFinished: ()-> Unit) {
        ioExecutor.execute {
            Timber.d( "inserting ${routes.size} routes")
            routeDao.insert(routes)
            insertFinished()
        }
    }

    /**
     * Request a LiveData<List<Route>> from the Dao, based on a route name. If the name contains
     * multiple words separated by spaces, then we're emulating the GitHub API behavior and allow
     * any characters between the words.
     * @param name routesitory name
     */
    fun get(): DataSource.Factory<Int, RouteEntity> {
        return routeDao.routes()
    }

}