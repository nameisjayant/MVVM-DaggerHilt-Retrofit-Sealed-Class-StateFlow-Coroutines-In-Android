package com.example.demo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.Model.Task
import com.example.demo.R

class TaskAdapter(private var taskList: List<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return  TaskViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.each_row,parent,false
        ))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.body.text=taskList[position].body
    }

    override fun getItemCount(): Int = taskList.size

    class TaskViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)
    {
        val body:TextView = itemView.findViewById(R.id.tasks)
    }

    fun setTask(taskList: List<Task>)
    {
        this.taskList=taskList
        notifyDataSetChanged()
    }
}