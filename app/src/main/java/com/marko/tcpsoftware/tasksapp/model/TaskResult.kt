package com.marko.tcpsoftware.tasksapp.model

import com.google.gson.annotations.SerializedName

data class TaskResult(
    @SerializedName("tasks") val taskList: MutableList<Task> = mutableListOf()
)