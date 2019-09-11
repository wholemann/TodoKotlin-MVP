package com.example.todokotlin

import android.app.Application
import android.util.Log

class TodoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        PreferenceHelper.init(this)

        if (!PreferenceHelper.firstRun) {
            PreferenceHelper.firstRun = true
            Log.d("TodoApp", "The value of our pref is: ${PreferenceHelper.firstRun}")
        }
    }
}