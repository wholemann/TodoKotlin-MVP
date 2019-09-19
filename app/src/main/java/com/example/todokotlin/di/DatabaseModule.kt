package com.example.todokotlin.di

import com.example.todokotlin.data.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModules = module {
    single { AppDatabase.getInstance(androidApplication()) }
    single(createdAtStart = false) { get<AppDatabase>().taskDao() }
}