package com.marko.tcpsoftware.tasksapp.util

import java.text.SimpleDateFormat
import java.util.*

class FormatDate {

    //private val serverFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    private val readableFormat = SimpleDateFormat("MMM dd yyyy", Locale.US)

    fun getReadableFormat(date : Date?) : String{
        return if(date==null)
            "-"
        else
            readableFormat.format(date)
    }

}