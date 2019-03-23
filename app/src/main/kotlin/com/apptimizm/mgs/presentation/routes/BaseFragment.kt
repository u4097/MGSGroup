package com.apptimizm.mgs.presentation.routes

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.apptimizm.mgs.App
import io.reactivex.disposables.CompositeDisposable
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent

/**
 * Created by Sitnikov Oleg
 * Date: 2019-03-22
 * Time: 16:51
 */

open class BaseFragment: Fragment() {
    protected var disposables = CompositeDisposable()

    override fun onStart() {
        super.onStart()
        hideKeyboard()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    fun isOnline(): Boolean {
        return App.instance.isOnline()
    }


    fun showKeyboard(editText: EditText?) {
        editText?.requestFocus()
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
    }

    fun hideKeyboard() {
        if (KeyboardVisibilityEvent.isKeyboardVisible(activity)) {
            val imm = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            var view = activity?.currentFocus
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }

}