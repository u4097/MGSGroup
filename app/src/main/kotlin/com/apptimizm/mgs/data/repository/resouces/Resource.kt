package com.apptimizm.mgs.data.repository.resouces


data class Resource<out T> constructor(
        val state: ResourceState,
        val data: T? = null,
        val error: String? = null
)
