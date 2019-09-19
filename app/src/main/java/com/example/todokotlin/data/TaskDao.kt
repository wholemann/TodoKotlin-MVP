package com.example.todokotlin.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun findAll(): LiveData<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE id = :id")
    fun findById(id: Long): LiveData<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(task: TaskEntity)

    @Delete
    fun delete(task: TaskEntity)
}