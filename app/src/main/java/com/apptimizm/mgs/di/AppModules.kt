package com.apptimizm.mgs.di

import com.apptimizm.mgs.App
import com.apptimizm.mgs.AppConfiguration
import com.apptimizm.mgs.cache.DiskLruCache
import com.apptimizm.mgs.cache.LruCache
import com.apptimizm.mgs.data.datasource.*
import com.apptimizm.mgs.data.repository.LoginRepositoryImpl
import com.apptimizm.mgs.data.repository.RouteRepositoryImpl
import com.apptimizm.mgs.data.repository.SettingRepositoryImpl
import com.apptimizm.mgs.datasource.cache.LoginCacheDataSourceImpl
import com.apptimizm.mgs.datasource.cache.RouteCacheDataSourceImpl
import com.apptimizm.mgs.datasource.cache.SettingCacheDataSourceImpl
import com.apptimizm.mgs.datasource.model.LoginResponseEntity
import com.apptimizm.mgs.datasource.model.SettingEntity
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.datasource.remote.LoginRemoteDataSourceImpl
import com.apptimizm.mgs.datasource.remote.RouteRemoteDataSourceImpl
import com.apptimizm.mgs.datasource.remote.SettingRemoteDataSourceImpl
import com.apptimizm.mgs.networking.LoginApi
import com.apptimizm.mgs.domain.repository.LoginRepository
import com.apptimizm.mgs.domain.repository.RouteRepository
import com.apptimizm.mgs.domain.repository.SettingRepository
import com.apptimizm.mgs.domain.usecases.LoginUseCase
import com.apptimizm.mgs.domain.usecases.RouteUseCase
import com.apptimizm.mgs.domain.usecases.SettingUseCase
import com.apptimizm.mgs.presentation.viewmodel.LoginViewModel
import com.apptimizm.mgs.presentation.viewmodel.RouteViewModel
import com.apptimizm.mgs.presentation.viewmodel.SettingViewModel
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
    viewModel { SettingViewModel(settingUseCase = get()) }
    viewModel { RouteViewModel(routeUseCase = get()) }
}

// USE CASES
val useCaseModule: Module = module {
    factory { LoginUseCase(loginRepository = get()) }
    factory { SettingUseCase(settingRepository = get()) }
    factory { RouteUseCase(routeRepository = get()) }
}

// REPOSITORY
val repositoryModule: Module = module {
    single { LoginRepositoryImpl(cacheDataSource = get(), remoteDataSource = get()) as LoginRepository }
    single { SettingRepositoryImpl(cacheDataSource = get(), remoteDataSource = get()) as SettingRepository }
    single { RouteRepositoryImpl(cacheDataSource = get(), remoteDataSource = get()) as RouteRepository }
}

// DATASOURCE (cache and remote)
val dataSourceModule: Module = module {
    // Login
    single { LoginRemoteDataSourceImpl(api = get()) as LoginRemoteDataSource }
    single { LoginCacheDataSourceImpl(cache = get(LOGIN_CACHE)) as LoginCacheDataSource }

    // Setting
    single { SettingRemoteDataSourceImpl(api = get()) as SettingRemoteDataSource }
    single { SettingCacheDataSourceImpl(cache = get(SETTING_CACHE)) as SettingCacheDataSource }

    // Route
    single { RouteRemoteDataSourceImpl(api = get()) as RouteRemoteDataSource }
    single { RouteCacheDataSourceImpl(cache = get(ROUTE_CACHE)) as RouteCacheDataSource }
}


// NETWORK API
val networkModule: Module = module {
    single { AppConfiguration.createLoginApi() }
    single { AppConfiguration.createSettingApi() }
    single { AppConfiguration.createRouteApi() }
}

// CACHE
val cacheModule: Module = module {
    single(name = LOGIN_CACHE) { DiskLruCache<LoginResponseEntity>(App.instance.dirForCache) }
    single(name = SETTING_CACHE) { DiskLruCache<SettingEntity>(App.instance.dirForCache) }
    single(name = ROUTE_CACHE) { LruCache<List<RouteEntity>>() }
}

private const val LOGIN_CACHE = "LOGIN_CACHE"
private const val SETTING_CACHE = "SETTING_CACHE"
private const val ROUTE_CACHE = "ROUTE_CACHE"
private const val SUIT_CACHE = "SUIT_CACHE"
private const val BACKGROUND_CACHE = "BACKGROUND_CACHE"



