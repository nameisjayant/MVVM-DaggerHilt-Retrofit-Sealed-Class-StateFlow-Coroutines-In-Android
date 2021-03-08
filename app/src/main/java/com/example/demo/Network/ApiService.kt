package com.example.demo.Network

import com.example.demo.Model.Post
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
   suspend fun getPost():List<Post>
}