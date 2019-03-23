package com.apptimizm.mgs.datasource.remote

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.data.repository.resouces.ResourceState
import com.apptimizm.mgs.datasource.model.ErrorResponseEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.Response
import timber.log.Timber


/**
 * Created by Oleg Sitnikov on 2019-02-12
 */

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): Resource<T>? {

        val result: Resource<T> = safeApiResource(call, errorMessage)

        when (result.state) {
            is ResourceState.LOADING -> {}
            is ResourceState.EMPTY_CACHE ->{}
            is ResourceState.SUCCESS ->{}
            is ResourceState.ERROR -> {
                Timber.e("$errorMessage  - ${result.error}")
            }
        }
        return result

    }


    private suspend fun <T : Any> safeApiResource(call: suspend () -> Response<T>, errorMessage: String): Resource<T> {
        val response = call.invoke()
        var message = ""
        if (response.isSuccessful) return Resource(ResourceState.SUCCESS, response.body())

        try {
            val errorJsonString = response.errorBody()?.string()
            message = errorJsonString.toString()
        } catch (e: Exception) {
            Timber.e(e)
        }

        return Resource(ResourceState.ERROR, null, message)
    }
}

