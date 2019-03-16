package com.apptimizm.mgs.domain.usecases

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.oleg.photodocs.domain.model.login.LoginModel
import com.oleg.photodocs.domain.repository.LoginRepository
import com.apptimizm.mgs.datasource.model.LoginResponseEntity


class LoginUseCase constructor(private val loginRepository: LoginRepository) {

    suspend fun get(loginModel: LoginModel): Resource<LoginResponseEntity>? =
        loginRepository.get(loginModel)

    suspend fun getToken(): Resource<String>? =
        loginRepository.getToken()
}
