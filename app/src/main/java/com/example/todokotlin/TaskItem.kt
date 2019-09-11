package com.example.todokotlin

import java.util.*

data class TaskItem(
    val title: String,
    val profileUrl: String = "",
    val userName: String,
    val dueDate: Date? = null,
    val commentCount: Int = 0
)