package com.apptimizm.mgs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.apptimizm.mgs.AppConfiguration.getRootViewContainerFor
import com.apptimizm.mgs.AppConfiguration.riseAndShine
import com.apptimizm.mgs.data.repository.resouces.ResourceState
import com.apptimizm.mgs.di.loadAppModules
import com.apptimizm.mgs.presentation.model.Login
import com.apptimizm.mgs.presentation.utils.date.DateTimeUtils.Companion.currentDate
import com.apptimizm.mgs.presentation.utils.date.DateTimeUtils.Companion.getUpdateDate
import com.apptimizm.mgs.presentation.utils.pref.PrefUtils
import com.apptimizm.mgs.presentation.viewmodel.LoginViewModel
import com.apptimizm.mgs.presentation.viewmodel.RouteViewModel
import com.apptimizm.mgs.presentation.viewmodel.SettingViewModel
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.longToast
import org.koin.androidx.viewmodel.ext.viewModel

interface ToolbarListener {
    fun updateTitle(title: String)
}

class MainActivity : AppCompatActivity(),
        ToolbarListener {
    override fun updateTitle(title: String) {
        toolbar.setTitle(title)
    }

    private val mSettingVm: SettingViewModel by viewModel()
    private val mRouteVm: RouteViewModel by viewModel()
    private lateinit var mNavController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get root container to put our app's UI in. For a debug build this will have our debug drawer.
        // For a release build this will be the Activity's root container.
        val container: ViewGroup = getRootViewContainerFor(this)
        val home: View = LayoutInflater.from(this).inflate(R.layout.activity_main, container, false)
        container.addView(home)

        // Wire up navigation drawer to open on toolbar button clicks.

//        Navigation
        mNavController = Navigation.findNavController(this, R.id.host_fragment)

        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.login_fragment -> {
                    supportActionBar?.hide()
                }
                R.id.route_fragment -> {
                    supportActionBar?.show()
                }
                else -> {
                    supportActionBar?.show()
                }

            }
        }


        setSupportActionBar(toolbar)
        supportActionBar?.hide()
        appBarConfiguration = AppBarConfiguration(mNavController.graph)
        setupActionBar(mNavController, appBarConfiguration)

        // Put refresh button in toolbar menu and have it refresh the games list.
        toolbar.inflateMenu(R.menu.main_menu)
        toolbar.setOnMenuItemClickListener {
            //            mSuitVm.getSuits(refresh = false)
//            mBackgroundVm.getBackgrounds(refresh = false)
            return@setOnMenuItemClickListener true
        }

        // Koin  DI init
        loadAppModules()

        // Wake up activity in devices on run
        riseAndShine(this)

        mSettingVm.settingEventResponse.observe(this@MainActivity, Observer {
            it?.let {
                when (it.state) {
                    ResourceState.LOADING -> {
                    }
                    ResourceState.ERROR -> {
                    }
                    ResourceState.SUCCESS -> {
                        PrefUtils.phone = it.data?.setting
                        longToast(PrefUtils.phone.toString())
                    }
                }
            }
        })

        mRouteVm.routes.observe(this@MainActivity, Observer {
            it?.let {
                when (it.state) {
                    ResourceState.LOADING -> {
                    }
                    ResourceState.ERROR -> {
                    }
                    ResourceState.SUCCESS -> {
//                        PrefUtils.phone = it.data?.route
                        longToast("Routes size: ${it.data?.results?.size}")
                    }
                }
            }
        })


    }

    private fun setupActionBar(
        navController: NavController,
        appBarConfig: AppBarConfiguration
    ) {
        setupActionBarWithNavController(navController, appBarConfig)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(mNavController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }



}
