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

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import org.jetbrains.anko.db.SqlOrderDirection

/**
 * Room data access object for accessing the [Route] table.
 */
@Dao
interface RouteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(routeList: List<RouteEntity>)

    @Update
    fun update(route: RouteEntity)

    @Query("SELECT * FROM route ORDER BY counterparty ASC")
    fun routes(): DataSource.Factory<Int,RouteEntity>

    @Query("DELETE FROM route")
    fun clear()

    @Query("SELECT * FROM route WHERE id = :routeId")
    fun routeById(routeId: String): LiveData<RouteEntity>

    @Query("SELECT * FROM route WHERE status = :pending")
    fun routesByPending(pending: String = "pending"):DataSource.Factory<Int,RouteEntity>

    @Query("SELECT * FROM route WHERE status = :status ORDER BY status ASC")
    fun routesByStatus(status: String): DataSource.Factory<Int,RouteEntity>

    @Query("SELECT * FROM route WHERE status = 'active' OR status = 'pending' ORDER BY counterparty ASC")
    fun routesActiveAndPending (): DataSource.Factory<Int,RouteEntity>
}