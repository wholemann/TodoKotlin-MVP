package com.example.todokotlin.di

import com.example.todokotlin.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { TaskViewModel(get()) }
}
