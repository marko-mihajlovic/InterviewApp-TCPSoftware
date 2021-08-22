package com.marko.tcpsoftware.tasksapp

import com.marko.tcpsoftware.tasksapp.util.getDateDiff
import com.marko.tcpsoftware.tasksapp.util.serverFormat
import org.junit.Test


class DateDifferenceTest {

    @Test
    fun dueInPast(){
        val nowDate = serverFormat.parse("2021-08-30")
        val dueDate = serverFormat.parse("2021-08-29")

        val dif = getDateDiff(nowDate, dueDate)
        print("dif $dif")
        assert(dif==0)
    }

    @Test
    fun dueInPast2(){
        val nowDate = serverFormat.parse("2021-08-30")
        val dueDate = serverFormat.parse("1976-04-20")

        val dif = getDateDiff(nowDate, dueDate)
        print("dif $dif")
        assert(dif==0)
    }

    @Test
    fun dueInNow(){
        val nowDate = serverFormat.parse("2021-08-30")
        val dueDate = serverFormat.parse("2021-08-30")

        val dif = getDateDiff(nowDate, dueDate)
        print("dif $dif")
        assert(dif==0)
    }

    @Test
    fun dueInFuture(){
        val nowDate = serverFormat.parse("2021-08-30")
        val dueDate = serverFormat.parse("2021-08-31")

        val dif = getDateDiff(nowDate, dueDate)
        print("dif $dif")
        assert(dif==1)
    }

    @Test
    fun dueInFuture2(){
        val nowDate = serverFormat.parse("2021-08-30")
        val dueDate = serverFormat.parse("2022-06-5")

        val dif = getDateDiff(nowDate, dueDate)
        print("dif $dif")
        assert(dif==279)
    }



}