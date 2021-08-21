package com.marko.tcpsoftware.tasksapp.model

import com.google.gson.annotations.SerializedName
import com.marko.tcpsoftware.tasksapp.util.getReadableFormat
import java.util.*

data class Task(
    @SerializedName("id") val id: String,
    @SerializedName("TargetDate") val targetDate: Date,
    @SerializedName("DueDate") val dueDate: Date,
    @SerializedName("Title") val title: String,
    @SerializedName("Description") val description: String,
    @SerializedName("Priority") val priority: Int,
){

    fun getTargetDateFormatted() : String{
        return getReadableFormat(targetDate)
    }

    fun getDueDateFormatted() : String{
        return getReadableFormat(dueDate)
    }

    fun toStringShort(): String {
        return "Task(priority=$priority, targetDate=${getTargetDateFormatted()} )"
    }


}