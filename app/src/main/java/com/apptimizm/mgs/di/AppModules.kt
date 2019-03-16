package com.apptimizm.mgs.di

import com.apptimizm.mgs.App
import com.apptimizm.mgs.AppConfiguration
import com.apptimizm.mgs.cache.DiskLruCache
import com.apptimizm.mgs.data.datasource.LoginCacheDataSource
import com.apptimizm.mgs.data.datasource.LoginRemoteDataSource
import com.apptimizm.mgs.data.repository.LoginRepositoryImpl
import com.apptimizm.mgs.datasource.cache.LoginCacheDataSourceImpl
import com.apptimizm.mgs.datasource.model.LoginResponseEntity
import com.apptimizm.mgs.datasource.remote.LoginRemoteDataSourceImpl
import com.apptimizm.mgs.networking.LoginApi
import com.oleg.photodocs.domain.repository.LoginRepository
import com.apptimizm.mgs.domain.usecases.LoginUseCase
import com.apptimizm.mgs.presentation.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun loadAppModules() = loadModules

private val loadModules by lazy {
    loadKoinModules(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        dataSourceModule,
        networkModule,
        cacheModule
    )
}

// VIEW MODEL`S
val viewModelModule: Module = module {
    viewModel { LoginViewModel(loginUseCase = get()) }
}

// USE CASES
val useCaseModule: Module = module {
    factory { LoginUseCase(loginRepository = get()) }
}

// REPOSITORY
val repositoryModule: Module = module {
    single { LoginRepositoryImpl(cacheDataSource = get(), remoteDataSource = get()) as LoginRepository }
}

// DATASOURCE (cache and remote)
val dataSourceModule: Module = module {
    // Login
    single { LoginRemoteDataSourceImpl(api = loginApi) as LoginRemoteDataSource }
    single { LoginCacheDataSourceImpl(cache = get(LOGIN_CACHE)) as LoginCacheDataSource }

}

private val loginApi: LoginApi = AppConfiguration.createLoginApi()

// NETWORK API
val networkModule: Module = module {
    single { loginApi }
}

private const val LOGIN_CACHE = "LOGIN_CACHE"
private const val DOCUMENT_CACHE = "DOCUMENT_CACHE"
private const val SUIT_CACHE = "SUIT_CACHE"
private const val BACKGROUND_CACHE = "BACKGROUND_CACHE"

// CACHE
val cacheModule: Module = module {
    single(name = LOGIN_CACHE) { DiskLruCache<LoginResponseEntity>(App.instance.dirForCache) }
}



