package com.marko.tcpsoftware.tasksapp.model

import com.google.gson.annotations.SerializedName
import com.marko.tcpsoftware.tasksapp.util.FormatDate
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
        return FormatDate().getReadableFormat(targetDate)
    }

    fun toStringShort(): String {
        return "Task(priority=$priority, targetDate=${getTargetDateFormatted()} )"
    }


}