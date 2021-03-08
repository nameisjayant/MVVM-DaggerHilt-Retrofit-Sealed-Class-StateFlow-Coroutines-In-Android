package com.example.demo.Network

import com.example.demo.Model.Post
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getPost():List<Post> = apiService.getPost()
}