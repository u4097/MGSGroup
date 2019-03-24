package com.apptimizm.mgs.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.LoginResponseEntity
import com.apptimizm.mgs.presentation.model.Login
import com.apptimizm.mgs.presentation.utils.livedata.SingleLiveEvent
import com.apptimizm.mgs.domain.usecases.LoginUseCase
import com.apptimizm.mgs.presentation.model.mapToDomain
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-07
 * Time: 22:22
 */


class LoginViewModel constructor(val loginUseCase: LoginUseCase) : AbstractViewModel() {

    override val pending: AtomicBoolean? = null
    override fun getRoutesFromServer(refresh: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listScrolled() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val loginEventResponse = SingleLiveEvent<Resource<LoginResponseEntity>>()


    fun login(login: Login) {
        scope.launch {
            val response = loginUseCase.get(loginModel = login.mapToDomain())
            loginEventResponse.postValue(response)
        }
    }


}