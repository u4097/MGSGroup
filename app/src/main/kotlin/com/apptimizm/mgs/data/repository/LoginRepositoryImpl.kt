package com.apptimizm.mgs.data.repository

import com.apptimizm.mgs.data.datasource.LoginCacheDataSource
import com.apptimizm.mgs.data.datasource.LoginRemoteDataSource
import com.apptimizm.mgs.data.repository.resouces.Resource
import com.apptimizm.mgs.data.repository.resouces.ResourceState
import com.apptimizm.mgs.domain.model.LoginModel
import com.apptimizm.mgs.domain.repository.LoginRepository
import com.apptimizm.mgs.datasource.model.LoginResponseEntity
import com.apptimizm.mgs.domain.model.mapToDataSource
import timber.log.Timber

class LoginRepositoryImpl constructor(
    private val cacheDataSource: LoginCacheDataSource,
    private val remoteDataSource: LoginRemoteDataSource
) : LoginRepository {


    override suspend fun get(loginModel: LoginModel): Resource<LoginResponseEntity>? =
        remoteDataSource.get(loginModel = loginModel.mapToDataSource())

}

