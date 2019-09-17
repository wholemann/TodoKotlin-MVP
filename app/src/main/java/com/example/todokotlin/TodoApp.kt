package com.example.todokotlin

import android.app.Application
import android.util.Log
import com.example.todokotlin.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TodoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TodoApp)
            modules(
                listOf(
                    viewModelModules,
                    roomModules,
                    repositoryModules,
                    networkModules,
                    apiModules
                )
            )
        }

        PreferenceHelper.init(this)

        if (!PreferenceHelper.firstRun) {
            PreferenceHelper.firstRun = true
            Log.d("TodoApp", "The value of our pref is: ${PreferenceHelper.firstRun}")
        }
    }
}