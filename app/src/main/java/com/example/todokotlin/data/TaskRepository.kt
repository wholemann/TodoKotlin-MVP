package com.example.todokotlin.data

import androidx.lifecycle.LiveData
import com.example.todokotlin.api.TodoService
import io.reactivex.Completable
import io.reactivex.Single

class TaskRepository(
    private val taskDao: TaskDao,
    private val service: TodoService
) {

    fun findAll(): Single<List<TaskEntity>> {
        return service.getTodos()
    }

    fun findById(id: Long): LiveData<TaskEntity> {
        return taskDao.findById(id)
    }

    fun save(task: TaskEntity): Completable {
        return Completable.fromCallable { taskDao.save(task) }
    }

    fun delete(task: TaskEntity): Completable {
        return Completable.fromCallable { taskDao.delete(task) }
    }
}