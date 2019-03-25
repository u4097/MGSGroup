package com.apptimizm.mgs

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.apptimizm.mgs.AppConfiguration.getRootViewContainerFor
import com.apptimizm.mgs.AppConfiguration.riseAndShine
import com.apptimizm.mgs.datasource.model.ErrorResponseEntity
import com.apptimizm.mgs.datasource.model.route.RouteEntity
import com.apptimizm.mgs.datasource.model.route.RouteUpdaterEntity
import com.apptimizm.mgs.di.*
import com.apptimizm.mgs.networking.OnUpdateListener
import com.apptimizm.mgs.networking.UpdateReceiver
import com.apptimizm.mgs.presentation.utils.pref.PrefUtils
import com.apptimizm.mgs.presentation.utils.view.gone
import com.apptimizm.mgs.presentation.utils.view.visible
import com.apptimizm.mgs.presentation.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.viewModel
import org.koin.core.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.inject
import org.koin.core.module.Module
import org.koin.dsl.module
import timber.log.Timber
import java.lang.Exception

interface ToolbarListener {
    fun updateTitle(title: String)
}

interface OnLoadingListener {
    fun onFinishLoading()
    fun onStartLoading()
}

class MainActivity : AppCompatActivity(),
    ToolbarListener,
    OnLoadingListener, KoinComponent, OnUpdateListener {
    override fun onUpdate() {
        mVm.getRoutesFromCacheByPending()
    }

    override fun onStartLoading() {
        progress.visible()
    }

    override fun onFinishLoading() {
        progress.gone()
    }

    override fun updateTitle(title: String) {
        toolbar.title = title
    }

    lateinit var mNavController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val mVm: MainViewModel by viewModel()

    val loadBRModule by lazy {
        loadKoinModules(
            broadcastReceiver
        )
    }

    val broadcastReceiver: Module = module {
        single { UpdateReceiver(routeUseCase = get(), onUpdateListener = this@MainActivity) }
        single {
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
                addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            }
        }
    }


    val br: UpdateReceiver by inject()
    val filter: IntentFilter by inject()

    private var currentDest: Int = 0


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
            currentDest = destination.id
            when (currentDest) {
                R.id.login_fragment -> {
                    supportActionBar?.hide()
                }
                R.id.route_fragment -> {
                    supportActionBar?.show()
                }
                R.id.support -> {
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

        // Koin  DI init
        loadAppModules()

        loadBRModules()

        mVm.routes.observe(this, Observer {
            Timber.tag("ROUTE").d("Pending routes size: ${it.size}")
            if (App.instance.isOnline()) {
                it.forEach {
                    mVm.updateRouteOnServer(
                        isOnline = true,
                        routeEntity = it,
                        route = RouteUpdaterEntity(it.factOnExportDatetime!!, it.talon, it.bugs!!),
                        id = it.id
                    )
                }
            } else {
//                longToast(getString(R.string.msg_no_internet_dialog_content))
                Timber.tag("ROUTE").d("No inet connection on route update!")
            }
        })

        mVm.serverError.observe(this, Observer<ErrorResponseEntity> {
            Timber.tag("ERROR").e("\uD83D\uDE28 Wooops ${it.statusCode} - ${it.errors?.detail}")
        })

        try {
            registerReceiver(br, filter)
        } catch (e: Exception) {
            Timber.e(e)
        }

        // Wake up activity in devices on run
        riseAndShine(this)


    }

    fun loadBRModules() = loadBRModule

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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> {
                when (currentDest) {
                    R.id.support -> {
                        mNavController.navigate(R.id.login_fragment)
                        return true
                    }
                    R.id.unFinishedDetail -> {
                        mNavController.navigate(R.id.route_fragment)
                        return true

                    }
                    R.id.finished_fragment -> {
                        mNavController.navigate(R.id.route_fragment)
                        return true
                    }
                    else -> {
                        mNavController.navigate(R.id.route_fragment)
                        return true
                    }
                }
            }
            R.id.login_fragment -> {
                MaterialDialog(this).show {
                    title(R.string.dialog_logout)
                    message(R.string.dialog_confirm_app_exit)
                    positiveButton(R.string.dialog_logout) {
                        PrefUtils.token = ""
                        PrefUtils.nextpage = 1
                        mVm.clearDb()
                        while (mNavController.popBackStack()) {
                            mNavController.popBackStack()
                        }
                        mNavController.navigate(R.id.login_fragment)
                    }
                    negativeButton(R.string.dialog_cancel) { }
                    cancelable(false)
                }

                return true
            }
        }
        return NavigationUI.onNavDestinationSelected(item!!, mNavController) ||
                super.onOptionsItemSelected(item)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        mNavController.navigate(R.id.route_fragment)
    }

}
