package com.example.todokotlin

import io.reactivex.Single
import retrofit2.http.GET

interface TodoService {
    @GET("/todos")
    fun getTodos(): Single<List<TaskEntity>>
}