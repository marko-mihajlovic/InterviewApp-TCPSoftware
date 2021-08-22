package com.marko.tcpsoftware.tasksapp.repository.tasks

import com.marko.tcpsoftware.tasksapp.model.Task
import com.marko.tcpsoftware.tasksapp.model.TaskResult
import com.marko.tcpsoftware.tasksapp.repository.BaseRepository
import retrofit2.Response
import java.util.*

class TasksRepository {

    suspend fun getTask(): Response<TaskResult> {
        return BaseRepository().getService(TasksService::class.java).getTasks()
    }

    fun defaultSort(taskList: List<Task>?): List<Task>? {
        return taskList?.sortedWith(
            compareByDescending<Task> { it.priority }
                .thenBy { it.targetDate })
    }

    fun filterByTargetDate(taskList: List<Task>?, targetDate: Date): List<Task>? {
        return taskList?.filter {
            it.targetDate == targetDate
        }
    }

    fun filterAndSort(taskList: List<Task>?, targetDate: Date?): List<Task>? {
        return if(targetDate == null){
            defaultSort(taskList)
        }else{
            defaultSort(filterByTargetDate(taskList, targetDate))
        }
    }
}