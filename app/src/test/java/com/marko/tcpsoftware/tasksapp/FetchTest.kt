package com.marko.tcpsoftware.tasksapp

import com.marko.tcpsoftware.tasksapp.model.Task
import com.marko.tcpsoftware.tasksapp.model.TaskResult
import com.marko.tcpsoftware.tasksapp.repository.tasks.TasksRepository
import com.marko.tcpsoftware.tasksapp.util.getTodayDate
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class FetchTest {

    @Test
    fun fetchV2() = runBlocking{
        val response = TasksRepository().getTasks()

        if (response.isSuccessful) {
            print("Successful\n")

            val taskResult : TaskResult? = response.body()
            Assert.assertNotNull(taskResult)

            for (task in taskResult!!.taskList) {
                println("task: $task")
            }
        } else {
            print("Error: ${response.code()}")
        }
    }

    @Test
    fun fetchAndSortV2() = runBlocking{
        val response = TasksRepository().getTasks()

        if (response.isSuccessful) {
            print("Successful\n")

            val taskResult : TaskResult? = response.body()
            Assert.assertNotNull(taskResult)

            val taskList : List<Task>? = TasksRepository().defaultSort(taskResult!!.taskList)

            for (task in taskList!!) {
                println("task: ${task.toStringShort()}")
            }
        } else {
            print("Error: ${response.code()}")
        }
    }

    @Test
    fun testTask() {

        val task = Task("id1", getTodayDate(), getTodayDate(), "title1", "desc1", 4, -1)
        println("task: ${task.toStringShort()}")

    }




}