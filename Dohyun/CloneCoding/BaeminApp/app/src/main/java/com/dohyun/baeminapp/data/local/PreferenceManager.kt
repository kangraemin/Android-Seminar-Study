package com.dohyun.baeminapp.data.local

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(ctx: Context) {

    private val pref : SharedPreferences = ctx.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE)

    var checkLogin: Boolean
        get() = pref.getBoolean(LOGIN, false)
        set(value) {
            val editor = pref.edit()
            editor.putBoolean(LOGIN, value)
            editor.apply()
        }

    companion object {
        private const val PREFERENCE = "Baemin"
        private const val LOGIN = "CHECK_LOGIN"
    }
}