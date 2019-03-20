package com.apptimizm.mgs

import android.app.Activity
import android.content.Context.POWER_SERVICE
import android.os.PowerManager
import android.os.PowerManager.*
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
import au.com.gridstone.debugdrawer.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.apptimizm.mgs.AppConfiguration.DatasourceProperties.DEV_URL
import com.apptimizm.mgs.AppConfiguration.DatasourceProperties.MOCK_URL
import com.apptimizm.mgs.mockapi.MockLoginApi
import com.apptimizm.mgs.mockapi.MockRouteApi
import com.apptimizm.mgs.mockapi.MockSettingApi
import com.apptimizm.mgs.networking.LoginApi
import com.apptimizm.mgs.networking.RouteApi
import com.apptimizm.mgs.networking.SettingApi
import com.apptimizm.mgs.presentation.utils.debugdrawer.VersionInfoModule
import com.apptimizm.mgs.presentation.utils.pref.PrefUtils
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import java.util.concurrent.TimeUnit

/** Init Network and debug drawer configuration class */

object AppConfiguration : KoinComponent {
    private val httpLogger by inject<HttpLogger>()
    private val debugRetrofitConfig by inject<DebugRetrofitConfig>()
    private val retrofit by inject<Retrofit>()
    private val mockRetrofit by inject<MockRetrofit>()

    object DatasourceProperties {
        const val DEV_URL = "http://178.57.222.1:8000/v0/"
        const val MOCK_URL = "http://localhost/mock/v0/"
    }


    // Network module
    val remoteDataSource = module {
        single { HttpLogger(get()) }
        single { NetworkBehavior.create() }
        single {
            DebugRetrofitConfig(
                get(),
                listOf(
                    Endpoint("Develop", DEV_URL, isMock = false),
                    Endpoint("Mock", MOCK_URL, isMock = true)
                ),
                get()
            )
        }
        single { httpClient() }
        single { retrofitClient(get()) }
        single {
            MockRetrofit.Builder(get()).networkBehavior(get()).build()
        }
    }


    // OkHTTP Client
    private fun httpClient(): OkHttpClient {
        // Logging Interceptor
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        // Http Client Builder
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)

        clientBuilder.addInterceptor(httpLoggingInterceptor)
        clientBuilder.addInterceptor(ChuckInterceptor(App.instance))
        clientBuilder.addInterceptor {
            val newRequest = it.request().newBuilder()
                .addHeader("Authorization", "Bearer ${PrefUtils.token}")
                .build()
            it.proceed(newRequest)
        }

        return clientBuilder.build()
    }

    // Retrofit
    private fun retrofitClient(httpClient: OkHttpClient): Retrofit {
        val currentEndpoint: Endpoint = debugRetrofitConfig.currentEndpoint
        return Retrofit.Builder()
            .baseUrl(currentEndpoint.url)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    }

    // API Services
    fun createLoginApi(): LoginApi =
        if (debugRetrofitConfig.currentEndpoint.isMock) {
            MockLoginApi(mockRetrofit)
        } else {
            retrofit.create<LoginApi>(LoginApi::class.java)
        }

    fun createSettingApi(): SettingApi =
        if (debugRetrofitConfig.currentEndpoint.isMock) {
            MockSettingApi(mockRetrofit)
        } else {
            retrofit.create<SettingApi>(SettingApi::class.java)
        }

    fun createRouteApi(): RouteApi =
        if (debugRetrofitConfig.currentEndpoint.isMock) {
            MockRouteApi(mockRetrofit)
        } else {
            retrofit.create<RouteApi>(RouteApi::class.java)
        }


    // Debug Drawer SetUp
    fun getRootViewContainerFor(activity: Activity): ViewGroup {
        return DebugDrawer.with(activity)
            .addSectionTitle("Network")
            .addModule(RetrofitModule(debugRetrofitConfig))
            .addSectionTitle("Logs")
            .addModule(OkHttpLoggerModule(httpLogger))
            .addModule(TimberModule())
            .addModule(LeakCanaryModule())
            .addSectionTitle("Device information")
            .addModule(DeviceInfoModule())
            .addSectionTitle("Version information")
            .addModule(VersionInfoModule())
            .buildMainContainer()
    }

    /**
     * Show the activity over the lock-screen and wake up the device. If you launched the app manually
     * both of these conditions are already true. If you deployed from the IDE, however, this will
     * save you from hundreds of power button presses and pattern swiping per day!
     */
    fun riseAndShine(activity: Activity) {
        activity.window.addFlags(FLAG_SHOW_WHEN_LOCKED);
        val power: PowerManager = activity.getSystemService(POWER_SERVICE) as PowerManager
        val lock: PowerManager.WakeLock = power.newWakeLock(
            FULL_WAKE_LOCK
                    or ACQUIRE_CAUSES_WAKEUP
                    or ON_AFTER_RELEASE,
            "photoDocks:wakeup!"
        )
        lock.acquire(5)
        lock.release()
    }

}