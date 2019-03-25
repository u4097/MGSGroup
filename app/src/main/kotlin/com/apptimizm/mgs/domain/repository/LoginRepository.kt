package com.apptimizm.mgs.domain.repository

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.ErrorResponseEntity
import com.apptimizm.mgs.domain.model.LoginModel
import com.apptimizm.mgs.datasource.model.LoginResponseEntity

interface LoginRepository {

    suspend fun get(loginModel: LoginModel): Resource<LoginResponseEntity>?

    suspend fun getRouteFromServer(refresh: Boolean, onSuccess:(size: Int)->Unit, onError: (error: String) -> Unit)

}
