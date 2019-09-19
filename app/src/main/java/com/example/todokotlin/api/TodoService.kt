package com.example.todokotlin.api

import com.example.todokotlin.data.TaskEntity
import io.reactivex.Single
import retrofit2.http.GET

interface TodoService {
    @GET("/todos")
    fun getTodos(): Single<List<TaskEntity>>
}