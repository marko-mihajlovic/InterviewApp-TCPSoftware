package com.marko.tcpsoftware.tasksapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marko.tcpsoftware.tasksapp.model.Task
import com.marko.tcpsoftware.tasksapp.repository.tasks.TasksRepository
import com.marko.tcpsoftware.tasksapp.util.getReadableFormat
import com.marko.tcpsoftware.tasksapp.util.getTodayDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.*

class TasksViewModel(
    private val today : Date = getTodayDate() // getTodayDate() or getDateExistInDB if we want to test app
) : ViewModel() {

    var selectedDate : Date = today

    private var taskList : MutableLiveData<List<Task>>? = null
    private var taskListForSelectedDay : MutableLiveData<List<Task>>? = null
    var titleDay : MutableLiveData<String> = MutableLiveData("Today")

    fun getTasksForSelectedDay(): MutableLiveData<List<Task>> {
        if (taskListForSelectedDay == null || taskList == null) {
            taskListForSelectedDay = MutableLiveData()
            taskList = MutableLiveData()

            loadTaskListAsync()
        }

        return taskListForSelectedDay!!
    }

    private val ioScope by lazy { CoroutineScope(SupervisorJob() + Dispatchers.Default) }
    private fun loadTaskListAsync(){
        ioScope.launch {
            loadTaskList()
        }
    }

    private suspend fun loadTaskList() {
        val response = TasksRepository().getTask()
        try {
            if (response.isSuccessful) {
                updateLists(response.body()?.taskList)
            } else {
                updateLists(mutableListOf())
            }
        } catch (e: HttpException) {
            updateLists(mutableListOf())
        } catch (e: Throwable) {
            updateLists(mutableListOf())
        }
    }


    private fun updateLists(taskList : List<Task>?){
        this.taskList?.postValue(taskList)
        updateSelectedDayList(taskList)
    }

    fun updateSelectedDateAndList(selectedDate : Date){
        this.selectedDate = selectedDate
        updateSelectedDayList(taskList?.value)
    }

    private fun updateSelectedDayList(taskList : List<Task>?){
        taskListForSelectedDay?.postValue(TasksRepository().filterAndSort(taskList, selectedDate))

        if(selectedDate==today){
            titleDay.postValue("Today")
        }else{
            titleDay.postValue(getReadableFormat(selectedDate))
        }

    }


}