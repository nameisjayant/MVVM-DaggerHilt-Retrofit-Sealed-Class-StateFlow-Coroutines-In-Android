package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.Adapter.TaskAdapter
import com.example.demo.Util.ConnectivityLiveData
import com.example.demo.Util.TaskState
import com.example.demo.ViewModel.MainViewModel
import com.example.demo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskAdapter: TaskAdapter

    private lateinit var connectivityLiveData: ConnectivityLiveData
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerview()
        mainViewModel.getTasks()
        connectivityLiveData= ConnectivityLiveData(application)
       connectivityLiveData.observe(this,{isAvailable->
           when(isAvailable)
           {
               true->{
                   lifecycleScope.launchWhenStarted {
                       mainViewModel.taskState.collect {
                           when (it) {
                               is TaskState.Empty -> {
                               }
                               is TaskState.Success -> {
                                   taskAdapter.setTask(it.data)
                                   binding.recyclerview.isVisible = true
                                   binding.progressBar.isVisible = false
                                   binding.networkFailed.isVisible = false
                               }
                               is TaskState.Failure -> {
                                   binding.recyclerview.isVisible = false
                                   binding.progressBar.isVisible = false
                                   binding.networkFailed.isVisible = false
                                   binding.error.isVisible=true

                               }
                               is TaskState.Loading -> {
                                   binding.recyclerview.isVisible = false
                                   binding.progressBar.isVisible = true
                                   binding.networkFailed.isVisible = false
                               }
                           }
                       }
                   }
               }
               false->{
                   binding.recyclerview.isVisible = false
                   binding.progressBar.isVisible = false
                   binding.error.isVisible=false
                   binding.networkFailed.isVisible = true
               }
           }
       })
    }

    private fun initRecyclerview() {
        taskAdapter = TaskAdapter(ArrayList())
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }
    }
}