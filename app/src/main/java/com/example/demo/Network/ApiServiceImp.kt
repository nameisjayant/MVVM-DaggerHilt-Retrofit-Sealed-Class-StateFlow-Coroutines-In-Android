package com.example.demo.Network

import com.example.demo.Model.Task
import javax.inject.Inject

class ApiServiceImp @Inject constructor(private val apiService: ApiService) {

    suspend fun getTasks():List<Task> = apiService.getTasks()
}