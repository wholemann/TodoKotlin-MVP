package com.example.todokotlin.di

import com.example.todokotlin.TaskRepository
import org.koin.dsl.module

val repositoryModules = module {
    single { TaskRepository(get(), get()) }
}