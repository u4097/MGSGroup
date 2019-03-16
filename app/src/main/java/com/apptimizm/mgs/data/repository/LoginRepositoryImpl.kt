package com.apptimizm.mgs.data.repository

import com.apptimizm.mgs.data.datasource.LoginCacheDataSource
import com.apptimizm.mgs.data.datasource.LoginRemoteDataSource
import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.data.repository.resouces.ResourceState
import com.oleg.photodocs.domain.model.login.LoginModel
import com.oleg.photodocs.domain.model.login.mapToDataSource
import com.oleg.photodocs.domain.repository.LoginRepository
import com.apptimizm.mgs.datasource.model.LoginResponseEntity

class LoginRepositoryImpl constructor(
    private val cacheDataSource: LoginCacheDataSource,
    private val remoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    override suspend fun getToken(): Resource<String>? {
        var token: String?
        var response = cacheDataSource.get()
        response.await().let {
            token = it
        }
        token?.let {
            if (!token?.isEmpty()!!) {
                return Resource(ResourceState.SUCCESS, token)
            } else {
                return Resource(ResourceState.ERROR, null, "Token is empty")
            }
        }
        return Resource(ResourceState.SUCCESS, token)
    }


        override suspend fun get(loginModel: LoginModel): Resource<LoginResponseEntity>? {
            val loginResponse = remoteDataSource.get(loginModel.mapToDataSource())
            cacheDataSource.set(loginResponse?.data?.token)
            return loginResponse
        }

    }

