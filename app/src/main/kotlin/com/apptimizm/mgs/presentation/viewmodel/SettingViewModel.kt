package com.apptimizm.mgs.presentation.viewmodel

import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.datasource.model.SettingEntity
import com.apptimizm.mgs.presentation.utils.livedata.SingleLiveEvent
import com.apptimizm.mgs.domain.usecases.SettingUseCase
import kotlinx.coroutines.launch

/**
 * Created by Oleg Sitnikov
 * Date: 2019-03-16
 * Time: 09:17
 */


class SettingViewModel constructor(val settingUseCase: SettingUseCase) : AbstractViewModel() {

    val settingEventResponse = SingleLiveEvent<Resource<SettingEntity>>()


    fun getPhone() {
        scope.launch {
            val response = settingUseCase.get()
            settingEventResponse.postValue(response)
        }
    }

}