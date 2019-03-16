package com.apptimizm.mgs.mockapi

import com.apptimizm.mgs.datasource.model.SettingEntity
import com.apptimizm.mgs.networking.SettingApi
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit

class MockSettingApi(mockRetrofit: MockRetrofit) : SettingApi {

    private val delegate: BehaviorDelegate<SettingApi> =
        mockRetrofit.create(SettingApi::class.java)

    override fun getPhoneAsync(): Deferred<Response<SettingEntity>> {
        val response = SettingEntity(phone)
        return delegate.returningResponse(response).getPhoneAsync()
    }

    private val phone =
        "+7 952 075 70 99"

}
