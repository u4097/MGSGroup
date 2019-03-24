package com.apptimizm.mgs.presentation.viewmodel

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.SettingEntity
import com.apptimizm.mgs.presentation.utils.livedata.SingleLiveEvent
import com.apptimizm.mgs.domain.usecases.SettingUseCase
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-16
 * Time: 09:17
 */


class SettingViewModel constructor(val settingUseCase: SettingUseCase) : AbstractViewModel() {

    override val pending: AtomicBoolean?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun getRoutesFromServer(refresh: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun listScrolled() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val settingEventResponse = SingleLiveEvent<Resource<SettingEntity>>()


    fun getPhone() {
        scope.launch {
            val response = settingUseCase.get()
            settingEventResponse.postValue(response)
        }
    }

}