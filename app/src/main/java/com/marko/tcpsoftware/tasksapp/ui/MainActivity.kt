package com.marko.tcpsoftware.tasksapp.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.marko.tcpsoftware.tasksapp.R
import com.marko.tcpsoftware.tasksapp.interfaces.OpenTaskDetailsListener
import com.marko.tcpsoftware.tasksapp.viewmodels.TasksViewModel

class MainActivity : AppCompatActivity(), OpenTaskDetailsListener {

    private val viewModel : TasksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(viewModel.selectedTask==null)
            openTaskList()
        else
            openTaskDetails()
    }

    private fun openTaskList(){
        viewModel.selectedTask = null
        toggleBackBtn(false)
        supportActionBar?.title = getString(R.string.app_name)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame, TasksFragment.newInstance(), "TasksFragment")
            commit()
        }
    }


    override fun openTaskDetails() {
        toggleBackBtn(true)
        supportActionBar?.title = getString(R.string.task_details)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame, TaskDetailsFragment.newInstance(), "TaskDetailsFragment")
            commit()
        }
    }


    private var backBtnVisible : Boolean = false
    private fun toggleBackBtn(visible: Boolean) {
        backBtnVisible = visible
        supportActionBar?.setDisplayHomeAsUpEnabled(visible)
        supportActionBar?.setDisplayShowHomeEnabled(visible)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        when {
            backBtnVisible -> openTaskList()
            viewModel.isTodaySelected() -> super.onBackPressed()
            else -> viewModel.updateSelectedDateAndList(viewModel.today)
        }
    }
}