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
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apptimizm.mgs.datasource.model.route.RouteEntity

/**
 * Room data access object for accessing the [Route] table.
 */
@Dao
interface RouteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(routeList: List<RouteEntity>)

    // Do a similar query as the search API:
    // Look for repos that contain the query string in the name or in the description
    // and order those results descending, by the number of stars and then by name
    @Query("SELECT * FROM route ORDER BY counterparty ASC")
    fun routes(): DataSource.Factory<Int,RouteEntity>

    @Query("DELETE FROM route")
    fun clear()
}