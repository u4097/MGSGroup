package com.apptimizm.mgs.domain.usecases

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.domain.model.LoginModel
import com.apptimizm.mgs.domain.repository.LoginRepository
import com.apptimizm.mgs.datasource.model.LoginResponseEntity


class LoginUseCase constructor(private val loginRepository: LoginRepository) {

    suspend fun get(loginModel: LoginModel): Resource<LoginResponseEntity>? =
        loginRepository.get(loginModel)
}
