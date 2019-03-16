package com.apptimizm.mgs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apptimizm.mgs.AppConfiguration.getRootViewContainerFor
import com.apptimizm.mgs.AppConfiguration.riseAndShine

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Get root container to put our app's UI in. For a debug build this will have our debug drawer.
        // For a release build this will be the Activity's root container.
        val container: ViewGroup = getRootViewContainerFor(this)
        val home: View = LayoutInflater.from(this).inflate(R.layout.activity_main, container, false)
        container.addView(home)


/*        // Koin  DI init
        loadAppModules()*/

        // Wake up activity in devices on run
        riseAndShine(this)
    }
}
