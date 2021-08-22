package com.marko.tcpsoftware.tasksapp

import com.marko.tcpsoftware.tasksapp.model.Task
import com.marko.tcpsoftware.tasksapp.model.TaskResult
import com.marko.tcpsoftware.tasksapp.repository.tasks.TasksRepository
import com.marko.tcpsoftware.tasksapp.util.getDateExistInDB
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FilterTest {

    @Test
    fun filterTest() = runBlocking{
        val response = TasksRepository().getTask()
        val taskResult : TaskResult = response.body()!!
        val taskList : List<Task> = TasksRepository().filterByTargetDate(taskResult.taskList, getDateExistInDB())!!

        println("taskSize: ${taskList.size}")

        for (task in taskList) {
            println("task: ${task.toStringShort()}")
        }
    }

    @Test
    fun filterAndSortTest() = runBlocking{
        val response = TasksRepository().getTask()
        val taskResult : TaskResult = response.body()!!
        val taskList : List<Task> = TasksRepository().filterAndSort(taskResult.taskList, getDateExistInDB())!!

        println("taskSize: ${taskList.size}")

        for (task in taskList) {
            println("task: ${task.toStringShort()}")
        }
    }


}