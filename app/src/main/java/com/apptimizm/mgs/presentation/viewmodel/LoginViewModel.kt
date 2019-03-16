package com.apptimizm.mgs.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.LoginResponseEntity
import com.apptimizm.mgs.presentation.model.login.Login
import com.apptimizm.mgs.presentation.utils.livedata.SingleLiveEvent
import com.apptimizm.mgs.domain.usecases.LoginUseCase
import com.apptimizm.mgs.presentation.model.login.mapToDomain
import kotlinx.coroutines.launch

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-07
 * Time: 22:22
 */


class LoginViewModel constructor(val loginUseCase: LoginUseCase) : AbstractViewModel() {

    val loginEventResponse = SingleLiveEvent<Resource<LoginResponseEntity>>()

    val tokenData = MutableLiveData<Resource<String>>()

    fun getToken() {
        scope.launch {
            val response = loginUseCase.getToken()
            tokenData.postValue(response)
        }
    }

    fun login(login: Login) {
        scope.launch {
            val response = loginUseCase.get(loginModel = login.mapToDomain())
            loginEventResponse.postValue(response)
        }
    }


}