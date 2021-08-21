package com.marko.tcpsoftware.tasksapp.repository.tasks

import com.marko.tcpsoftware.tasksapp.model.TaskResult
import retrofit2.Call
import retrofit2.http.GET

interface TasksService {

    @GET("tasks")
    fun getTasks(): Call<TaskResult?>?

}