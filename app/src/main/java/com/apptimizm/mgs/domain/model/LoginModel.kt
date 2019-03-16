package com.apptimizm.mgs.domain.model

import com.apptimizm.mgs.datasource.model.LoginEntity
import com.apptimizm.mgs.domain.model.LoginModel

data class LoginModel(
    val login: String? = "NoName",
    val password: String?
)


fun LoginModel.mapToDataSource(): LoginEntity =
    LoginEntity(
        login = this.login,
        password = this.password
    )