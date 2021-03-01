package com.example.demo.Repository

import com.example.demo.Model.Task
import com.example.demo.Network.ApiServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiServiceImp: ApiServiceImp) {

    fun getTasks():Flow<List<Task>> = flow {
        emit(apiServiceImp.getTasks())
    }.flowOn(Dispatchers.IO)

}