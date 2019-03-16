package com.apptimizm.mgs.presentation.utils.view

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import com.apptimizm.mgs.R


object DialogUtils {

    fun createSplashErrorDialog(context: Context, content: String): MaterialDialog =
        MaterialDialog(context).show {
            title(R.string.dialog_error)
            message(R.string.dialog_error)
            positiveButton(R.string.dialog_repeat)
            negativeButton(R.string.dialog_exit)
            cancelable(false)
        }

    fun createSplashExitDialog(
        context: Context, content: String
    ): MaterialDialog =
        MaterialDialog(context).show {
            title(R.string.dialog_logout)
            message(R.string.dialog_confirm_app_exit)
            positiveButton(R.string.dialog_logout)
            negativeButton(R.string.dialog_cancel)
            cancelable(false)
        }

}