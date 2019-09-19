package com.example.todokotlin.data

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private const val PREFS_FILENAME = "prefs"
    private lateinit var prefs: SharedPreferences

    private val IS_FIRST_RUN_PREF = Pair("is_first_run", false)
    private val IS_SIGN_IN = Pair("is_sign_in", false)

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
    }

    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var firstRun: Boolean
        get() = prefs.getBoolean(IS_FIRST_RUN_PREF.first, IS_FIRST_RUN_PREF.second)
        set(value) = prefs.edit {
            it.putBoolean(IS_FIRST_RUN_PREF.first, value)
        }

    var signIn: Boolean
        get() = prefs.getBoolean(IS_SIGN_IN.first, IS_SIGN_IN.second)
        set(value) = prefs.edit {
            it.putBoolean(IS_SIGN_IN.first, value)
        }
}