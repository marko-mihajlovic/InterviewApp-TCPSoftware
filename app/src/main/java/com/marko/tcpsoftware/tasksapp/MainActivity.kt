package com.marko.tcpsoftware.tasksapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marko.tcpsoftware.tasksapp.ui.TasksFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tasksFragment : TasksFragment = TasksFragment.newInstance()

       supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame, tasksFragment, "TasksFragment")
            show(tasksFragment)
            commit()
        }

    }


}