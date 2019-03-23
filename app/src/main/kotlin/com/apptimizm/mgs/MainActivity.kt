package com.apptimizm.mgs

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.apptimizm.mgs.AppConfiguration.getRootViewContainerFor
import com.apptimizm.mgs.AppConfiguration.riseAndShine
import com.apptimizm.mgs.di.loadAppModules
import com.apptimizm.mgs.presentation.utils.pref.PrefUtils
import com.apptimizm.mgs.presentation.utils.view.gone
import com.apptimizm.mgs.presentation.utils.view.visible
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

interface ToolbarListener {
    fun updateTitle(title: String)
}

interface OnLoadingListener {
    fun onFinishLoading()
    fun onStartLoading()
}

class MainActivity : AppCompatActivity(),
    ToolbarListener,
    OnLoadingListener {

    override fun onStartLoading() {
        progress.visible()
    }

    override fun onFinishLoading() {
        progress.gone()
    }

    override fun updateTitle(title: String) {
        toolbar.setTitle(title)
    }

    lateinit var mNavController: NavController
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


        // Koin  DI init
        loadAppModules()

        // Wake up activity in devices on run
        riseAndShine(this)

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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> {
                mNavController.navigate(R.id.route_fragment)
//                Timber.tag("ROUTE").d("On home click")
                return true
            }
            R.id.login_fragment -> {
                MaterialDialog(this).show {
                    title(R.string.dialog_logout)
                    message(R.string.dialog_confirm_app_exit)
                    positiveButton(R.string.dialog_logout) {
                        PrefUtils.token = ""
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
