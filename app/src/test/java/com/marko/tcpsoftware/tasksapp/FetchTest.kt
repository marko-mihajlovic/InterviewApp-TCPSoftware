package com.marko.tcpsoftware.tasksapp

import com.marko.tcpsoftware.tasksapp.model.Task
import com.marko.tcpsoftware.tasksapp.model.TaskResult
import com.marko.tcpsoftware.tasksapp.repository.tasks.TasksRepository
import org.junit.Assert
import org.junit.Test

class FetchTest {

    @Test
    fun testFetchTasks() {
        val taskResult : TaskResult? = TasksRepository().makeCall()?.execute()?.body()
        Assert.assertNotNull(taskResult)

        for (task in taskResult!!.taskList) {
            println("task: $task")
        }
    }

    @Test
    fun testFetchAndSortTasks() {
        val taskResult : TaskResult? = TasksRepository().makeCall()?.execute()?.body()
        Assert.assertNotNull(taskResult)

        val taskList: List<Task> = TasksRepository().defaultSort(taskResult!!.taskList)

        for (task in taskList) {
            println("task: ${task.toStringShort()}")
        }
    }
}