package com.apptimizm.mgs.presentation.utils.pref

object PrefUtils {
    private const val TOKEN = "token"

    var token: String?
        get() = SharedPrefFactory.prefs.getString(TOKEN, "")
        set(token) {
            SharedPrefFactory.editor.putString(TOKEN, token).commit()
        }
}
