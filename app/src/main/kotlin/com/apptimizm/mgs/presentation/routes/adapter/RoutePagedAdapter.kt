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

package com.apptimizm.mgs.presentation.routes.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.apptimizm.mgs.datasource.model.route.RouteEntity

/**
 * Adapter for the list of routesitories.
 */
class RoutePagedAdapter : PagedListAdapter<RouteEntity, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RouteViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val routeItem = getItem(position)
        if (routeItem != null) {
            (holder as RouteViewHolder).bind(routeItem)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<RouteEntity>() {
            override fun areItemsTheSame(oldItem: RouteEntity, newItem: RouteEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: RouteEntity, newItem: RouteEntity): Boolean =
                oldItem.address == newItem.address
        }
    }
}