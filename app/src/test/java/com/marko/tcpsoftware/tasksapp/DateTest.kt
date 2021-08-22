package com.marko.tcpsoftware.tasksapp

import com.marko.tcpsoftware.tasksapp.util.getReadableFormat
import com.marko.tcpsoftware.tasksapp.util.getTodayDate
import org.junit.Test

class DateTest {

    @Test
    fun dateToday(){

        print(getTodayDate())
        print("\n")
        print(getReadableFormat(getTodayDate()))

    }

}