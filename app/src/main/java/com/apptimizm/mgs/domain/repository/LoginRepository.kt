package com.oleg.photodocs.domain.repository

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.login.LoginModel
import com.apptimizm.mgs.datasource.model.LoginResponseEntity

interface LoginRepository {

    suspend fun get(loginModel: LoginModel): Resource<LoginResponseEntity>?

    suspend fun getToken(): Resource<String>?

}
