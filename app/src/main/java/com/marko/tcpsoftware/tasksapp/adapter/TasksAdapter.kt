package com.marko.tcpsoftware.tasksapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.recyclerview.widget.RecyclerView
import com.marko.tcpsoftware.tasksapp.R
import com.marko.tcpsoftware.tasksapp.databinding.RowTaskBinding
import com.marko.tcpsoftware.tasksapp.interfaces.OpenTaskDetailsListener
import com.marko.tcpsoftware.tasksapp.model.Task
import com.marko.tcpsoftware.tasksapp.util.*
import com.marko.tcpsoftware.tasksapp.viewmodels.TasksViewModel
import java.util.*

class TasksAdapter(
    private val context: Context,
    private val viewModel: TasksViewModel,
    private val openTaskDetailsListener : OpenTaskDetailsListener?
) : RecyclerView.Adapter<TasksAdapter.RowTasks>() {

    private var taskList: List<Task> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateTaskList(taskList: List<Task>?) {
        this.taskList = taskList ?: mutableListOf()

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    class RowTasks(var rowTaskBinding: RowTaskBinding) : RecyclerView.ViewHolder(rowTaskBinding.root)


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

        toggleStatusUI(binding, task)

        binding.root.setOnClickListener{
            viewModel.selectedTask.postValue(task)
            openTaskDetailsListener?.openTaskDetails()
        }
    }

    private fun toggleStatusUI(binding: RowTaskBinding, task: Task){
        when(task.status){
            STATUS_RESOLVED -> {
                toggleTaskValueTextColor(binding, R.color.green)
                setImage(context, binding.taskStatusImg, R.drawable.sign_resolved)
            }
            STATUS_CANNOT_RESOLVE-> {
                toggleTaskValueTextColor(binding, R.color.red)
                setImage(context, binding.taskStatusImg, R.drawable.unresolved_sign)
            }
            else -> { //STATUS_UNRESOLVED
                toggleTaskValueTextColor(binding, R.color.red)
                setImage(context, binding.taskStatusImg, 0, View.GONE)
            }
        }
    }

    private fun toggleTaskValueTextColor(binding: RowTaskBinding, @ColorRes color : Int){
        setTextColor(context, binding.taskTitleTxt, color)
        setTextColor(context, binding.dueDateValueTxt, color)
        setTextColor(context, binding.daysLeftValueTxt, color)
    }



}