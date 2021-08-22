package com.marko.tcpsoftware.tasksapp

import com.marko.tcpsoftware.tasksapp.util.dateMinusOneDay
import com.marko.tcpsoftware.tasksapp.util.datePlusOneDay
import com.marko.tcpsoftware.tasksapp.util.getReadableFormat
import com.marko.tcpsoftware.tasksapp.util.serverFormat
import org.junit.Test

class DateIncrementTest {

    @Test
    fun datePlusOne1(){

        var date = serverFormat.parse("2021-08-31")

        date = datePlusOneDay(date!!)

        print(getReadableFormat(date))

    }

    @Test
    fun datePlusOne2(){

        var date = serverFormat.parse("2020-02-28")

        date = datePlusOneDay(date!!)

        print(getReadableFormat(date))

    }

    @Test
    fun datePlusOne3(){

        var date = serverFormat.parse("2021-02-28")

        date = datePlusOneDay(date!!)

        print(getReadableFormat(date))

    }


    @Test
    fun dateMinusOne1(){

        var date = serverFormat.parse("2021-09-01")

        date = dateMinusOneDay(date!!)

        print(getReadableFormat(date))

    }


}