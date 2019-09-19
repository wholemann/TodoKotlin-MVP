package com.example.todokotlin.di

import com.example.todokotlin.api.TodoService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModules = module {
    single(createdAtStart = false) { get<Retrofit>().create(TodoService::class.java) }
}