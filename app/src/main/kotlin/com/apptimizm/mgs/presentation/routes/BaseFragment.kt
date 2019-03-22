package com.apptimizm.mgs.presentation.routes

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Sitnikov Oleg
 * Date: 2019-03-22
 * Time: 16:51
 */

open class BaseFragment: Fragment() {
    protected var disposables = CompositeDisposable()

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}