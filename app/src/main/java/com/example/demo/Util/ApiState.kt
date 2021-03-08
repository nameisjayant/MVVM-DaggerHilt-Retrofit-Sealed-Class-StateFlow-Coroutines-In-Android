package com.example.demo.Util

import com.example.demo.Model.Post

sealed class ApiState{
    object Loading : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    class Success(val data:List<Post>) : ApiState()
    object Empty : ApiState()
}
