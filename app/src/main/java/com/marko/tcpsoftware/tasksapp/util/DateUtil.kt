package com.marko.tcpsoftware.tasksapp.util

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.floor


val serverFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
private val readableFormat = SimpleDateFormat("MMM dd yyyy", Locale.US)

fun getReadableFormat(date : Date?) : String{
    return if(date==null)
        "-"
    else
        readableFormat.format(date)
}

fun getDateDiff(date1: Date?, date2: Date?): Int {
    if(date1 == null || date2 == null)
        return 0

    val dif = date2.time - date1.time
    if(dif<1)
        return 0

    return floor((dif/ (1000 * 60 * 60 * 24).toFloat()).toDouble()).toInt()
}

