package com.apptimizm.mgs.presentation.model

import com.apptimizm.mgs.domain.model.LoginModel

data class Login(
    val login: String?,
    val password: String?
)


fun Login.mapToDomain(): LoginModel = LoginModel(
    login = this.login,
    password = this.password
)