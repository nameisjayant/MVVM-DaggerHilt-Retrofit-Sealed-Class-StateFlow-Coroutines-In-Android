package com.example.demo.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo.Repository.MainRepository
import com.example.demo.Util.TaskState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(private val mainRepository: MainRepository) : ViewModel() {

    private val _taskState:MutableStateFlow<TaskState> = MutableStateFlow(TaskState.Empty)
    val taskState:StateFlow<TaskState> = _taskState


    fun getTasks() = viewModelScope.launch {
        _taskState.value=TaskState.Loading
        mainRepository.getTasks()
            .catch { e->
                _taskState.value=TaskState.Failure(e)
            }.collect { response->
                _taskState.value=TaskState.Success(response)
            }
    }
}