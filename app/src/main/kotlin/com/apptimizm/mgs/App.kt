package com.apptimizm.mgs

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import au.com.gridstone.debugdrawer.LumberYard
import com.apptimizm.mgs.AppConfiguration.remoteDataSource
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import timber.log.Timber
import java.io.File


class App : Application() {

    var dirForCache: File? = null

    override fun onCreate() {
        super.onCreate()
        instance = this

        if (LeakCanary.isInAnalyzerProcess(this)) return

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        LeakCanary.install(this)

        LumberYard.install(this)

        this.dirForCache = File(getRootAppDir())

        // Unique initialization of Dependency Injection library to allow the use of application context
        startKoin { androidContext(this@App) }

        loadKoinModules(remoteDataSource)

        Stetho.initializeWithDefaults(this);
    }

    private fun getRootAppDir(): String? {
        val m = packageManager
        var s = packageName
        val p = m.getPackageInfo(s, 0)
        return p.applicationInfo.dataDir
    }


    companion object {
        lateinit var instance: App
    }

    fun isOnline(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

}