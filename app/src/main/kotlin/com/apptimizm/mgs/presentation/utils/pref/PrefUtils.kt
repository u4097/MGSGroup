package com.apptimizm.mgs.presentation.utils.pref

object PrefUtils {
    private const val TOKEN = "token"
    private const val PHONE = "phone"
    private const val NEXT_PAGE = "nextpage"
    private const val LAST_PAGE = "nextpage"

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

    var nextpage: Int
        get() = SharedPrefFactory.prefs.getInt(NEXT_PAGE, 1)
        set(nextpage) {
            SharedPrefFactory.editor.putInt(NEXT_PAGE, nextpage).commit()
        }

    var lastpage: Int
        get() = SharedPrefFactory.prefs.getInt(NEXT_PAGE, 1)
        set(lastpage) {
            SharedPrefFactory.editor.putInt(NEXT_PAGE, lastpage).commit()
        }
}
