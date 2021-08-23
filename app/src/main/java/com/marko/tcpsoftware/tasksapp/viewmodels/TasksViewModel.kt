package com.marko.tcpsoftware.tasksapp.viewmodels

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
import java.util.*

class TasksViewModel : ViewModel() {

    val today : Date = getTodayDate() // getTodayDate() or getDateExistInDB if we want to test app
    var selectedDate : Date = today

    private var repository : TasksRepository = TasksRepository()

    private var taskList : MutableLiveData<List<Task>>? = null
    private var taskListForSelectedDay : MutableLiveData<List<Task>>? = null
    var titleDay : MutableLiveData<String> = MutableLiveData("Today")
    var selectedTask : MutableLiveData<Task> = MutableLiveData()

    fun getSelectedTask() : Task?{
        return selectedTask.value
    }


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
            updateLists(repository.getTasksOrEmpty())
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
        taskListForSelectedDay?.postValue(repository.filterAndSort(taskList, selectedDate))

        if(isTodaySelected()){
            titleDay.postValue("Today")
        }else{
            titleDay.postValue(getReadableFormat(selectedDate))
        }
    }

    fun updateStatus(newStatus: Int){
        selectedTask.postValue(getSelectedTask()?.apply { status = newStatus })
        taskList?.value?.singleOrNull{ it.id == getSelectedTask()?.id }?.apply { status = newStatus }
        taskListForSelectedDay?.value?.singleOrNull{ it.id == getSelectedTask()?.id }?.apply { status = newStatus }
    }


    fun isTodaySelected(): Boolean {
        return selectedDate==today
    }
}