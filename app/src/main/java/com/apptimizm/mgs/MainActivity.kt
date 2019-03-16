package com.apptimizm.mgs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.apptimizm.mgs.AppConfiguration.getRootViewContainerFor
import com.apptimizm.mgs.AppConfiguration.riseAndShine
import com.apptimizm.mgs.data.repository.resouces.ResourceState
import com.apptimizm.mgs.di.loadAppModules
import com.apptimizm.mgs.presentation.model.login.Login
import com.apptimizm.mgs.presentation.utils.pref.PrefUtils
import com.apptimizm.mgs.presentation.viewmodel.LoginViewModel
import org.jetbrains.anko.longToast
import org.koin.androidx.viewmodel.ext.viewModel

class MainActivity : AppCompatActivity() {

    private val mLoginVm: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get root container to put our app's UI in. For a debug build this will have our debug drawer.
        // For a release build this will be the Activity's root container.
        val container: ViewGroup = getRootViewContainerFor(this)
        val home: View = LayoutInflater.from(this).inflate(R.layout.activity_main, container, false)
        container.addView(home)


        // Koin  DI init
        loadAppModules()

        // Wake up activity in devices on run
        riseAndShine(this)

        mLoginVm.loginEventResponse.observe(this@MainActivity, Observer {
            it?.let {
                when (it.state) {
                    ResourceState.LOADING -> {
                    }
                    ResourceState.ERROR -> {
                    }
                    ResourceState.SUCCESS -> {
                        PrefUtils.token = it.data?.token
                        longToast(PrefUtils.token.toString())
                    }
                }
            }
        })

    }

    override fun onStart() {
        super.onStart()
        mLoginVm.login(Login(login = "Admin", password = "admin2018"))
    }
}
