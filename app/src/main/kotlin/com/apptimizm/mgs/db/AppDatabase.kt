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

import android.content.Context
import androidx.room.*
import com.apptimizm.mgs.datasource.model.route.BugEntity
import com.apptimizm.mgs.datasource.model.route.BugTypeConverter
import com.apptimizm.mgs.datasource.model.route.RouteEntity

/**
 * Database schema that holds the list of routes.
 */
@Database(
    entities = [RouteEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(BugTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun routeDao(): RouteDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "Route.db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}