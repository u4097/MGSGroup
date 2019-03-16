package com.apptimizm.mgs.datasource.model

import com.apptimizm.mgs.domain.model.LoginModel
import com.squareup.moshi.Json

class LoginEntity(


    @field:Json(name = "login")
    val login: String?,

    @field:Json(name = "password")
    val password: String?

)

fun LoginEntity.mapToDomain(): LoginModel =
    LoginModel(
        login = this.login,
        password = this.password
    )
