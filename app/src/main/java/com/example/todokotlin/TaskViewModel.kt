package com.example.todokotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TaskRepository(application)
    private val tasks: LiveData<List<TaskEntity>> = repository.findAll()


    fun findAll(): LiveData<List<TaskEntity>> {
        return tasks
    }

    fun save(task: TaskEntity) {
        repository.save(task)
    }

    fun delete(task: TaskEntity) {
        repository.delete(task)
    }
}