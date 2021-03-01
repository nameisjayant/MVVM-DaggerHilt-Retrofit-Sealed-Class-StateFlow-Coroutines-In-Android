package com.example.demo.Network

import com.example.demo.Model.Task
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getTasks():List<Task>
}