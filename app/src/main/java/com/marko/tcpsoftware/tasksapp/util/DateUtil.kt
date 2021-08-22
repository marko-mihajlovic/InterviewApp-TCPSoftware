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


fun getTodayDate(): Date {
    return serverFormat.parse(serverFormat.format(Date())) ?: Calendar.getInstance().apply {
        set(Calendar.HOUR, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
        set(Calendar.HOUR_OF_DAY, 0)
    }.time
}


fun getDateExistInDB(): Date {
    return serverFormat.parse("2021-09-10") ?: getTodayDate()
}

fun getDateNoneInDB(): Date {
    return serverFormat.parse("2019-05-02")  ?: getTodayDate()
}

fun getDateNoneInDB2(): Date {
    return serverFormat.parse("2021-09-09")  ?: getTodayDate()
}

fun getDateNoneInDB3(): Date {
    return serverFormat.parse("2021-08-22")  ?: getTodayDate()
}


fun datePlusOneDay(date: Date): Date {
    return Calendar.getInstance().apply {
        time = date
        add(Calendar.DATE, 1)
    }.time
}

fun dateMinusOneDay(date: Date): Date {
    return Calendar.getInstance().apply {
        time = date
        add(Calendar.DATE, -1)
    }.time
}