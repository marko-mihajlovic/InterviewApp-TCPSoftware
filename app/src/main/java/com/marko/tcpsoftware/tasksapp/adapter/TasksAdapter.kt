package com.marko.tcpsoftware.tasksapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marko.tcpsoftware.tasksapp.databinding.RowTaskBinding
import com.marko.tcpsoftware.tasksapp.model.Task
import com.marko.tcpsoftware.tasksapp.util.getDateDiff
import java.util.*

class TasksAdapter : RecyclerView.Adapter<TasksAdapter.RowTasks>() {

    private var taskList: List<Task> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateTaskList(taskList: List<Task>?) {
        this.taskList = taskList ?: mutableListOf()

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowTasks {
        val rowNewsBinding: RowTaskBinding = RowTaskBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RowTasks(rowNewsBinding)
    }

    override fun onBindViewHolder(rowNews: RowTasks, position: Int) {
        val binding: RowTaskBinding = rowNews.rowTaskBinding
        val task: Task = taskList[position]

        binding.taskTitleTxt.text = task.title
        binding.dueDateValueTxt.text = task.getDueDateFormatted()
        binding.daysLeftValueTxt.text = getDateDiff(Calendar.getInstance().time, task.dueDate).toString()

    }


    override fun getItemCount(): Int {
        return taskList.size
    }

    class RowTasks(var rowTaskBinding: RowTaskBinding) : RecyclerView.ViewHolder(rowTaskBinding.root)

}