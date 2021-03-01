package com.example.demo.Util

import com.example.demo.Model.Task

sealed class TaskState {
    object Empty :TaskState()
    object Loading:TaskState()
    class Success(val data:List<Task>) : TaskState()
    class Failure(val message:Throwable) : TaskState()
}