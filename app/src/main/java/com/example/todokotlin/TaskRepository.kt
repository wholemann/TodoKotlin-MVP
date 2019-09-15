package com.example.todokotlin

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import java.lang.reflect.Constructor

class TaskRepository constructor(application: Application) {

    private val db: AppDatabase = AppDatabase.getInstance(application)!!
    private val taskDao: TaskDao = db.taskDao()
    private val tasks: LiveData<List<TaskEntity>> = taskDao.findAll()

    fun findAll(): LiveData<List<TaskEntity>> {
        return tasks
    }

    fun save(task: TaskEntity) {
        SaveAsyncTask(taskDao).execute(task)
//        taskDao.save(task)
    }

    fun delete(task: TaskEntity) {
        taskDao.delete(task)
    }

    companion object {
        class SaveAsyncTask constructor(private val mAsyncTaskDao: TaskDao) : AsyncTask<TaskEntity, Void, Void>() {

            override fun doInBackground(vararg params: TaskEntity?): Void? {
                params[0]?.let { mAsyncTaskDao.save(it) }
                return null
            }

        }
    }
}