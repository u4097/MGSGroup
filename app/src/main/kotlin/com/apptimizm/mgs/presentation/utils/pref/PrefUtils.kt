package com.apptimizm.mgs.presentation.utils.pref

object PrefUtils {
    private const val TOKEN = "token"
    private const val PHONE = "phone"

    var token: String?
        get() = SharedPrefFactory.prefs.getString(TOKEN, "")
        set(token) {
            SharedPrefFactory.editor.putString(TOKEN, token).commit()
        }

    var phone: String?
        get() = SharedPrefFactory.prefs.getString(PHONE, "")
        set(phone) {
            SharedPrefFactory.editor.putString(PHONE, phone).commit()
        }
}
