package com.example.todokotlin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp
import java.util.*

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "user_id") var userId: Int,
    @ColumnInfo(name = "username") var userName: String = "wholeman",
    @ColumnInfo(name = "completed") var completed: Boolean = false,
    @ColumnInfo(name = "due_date") var dueDate: Long? = null,
    @ColumnInfo(name = "comment_count") var commentCount: Int = 0
)