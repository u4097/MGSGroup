package com.apptimizm.mgs.datasource.model

import com.squareup.moshi.Json

data class ErrorResponseEntity(
    @field:Json(name = "errors")
    val errors: Errors? = Errors(),
    @field:Json(name = "status_code")
    val statusCode: Int? = 0,
    @field:Json(name = "is_error")
    val isError: Boolean? = false
)

data class Errors(
    @field:Json(name = "detail")
    val detail: String? = ""
)
