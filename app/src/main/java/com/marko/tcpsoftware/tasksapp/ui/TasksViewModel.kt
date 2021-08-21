package com.marko.tcpsoftware.tasksapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marko.tcpsoftware.tasksapp.model.Task
import com.marko.tcpsoftware.tasksapp.repository.tasks.TasksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TasksViewModel : ViewModel() {

    val taskList : MutableLiveData<List<Task>> by lazy {
        MutableLiveData<List<Task>>().also {
            loadTaskListAsync(it)
        }
    }

    private val ioScope by lazy { CoroutineScope(SupervisorJob() + Dispatchers.Default) }
    private fun loadTaskListAsync(it : MutableLiveData<List<Task>>){
        ioScope.launch {
            loadTaskList(it)
        }
    }

    private suspend fun loadTaskList(it : MutableLiveData<List<Task>>) {
        val response = TasksRepository().getTask()
        try {
            if (response.isSuccessful) {
                val taskList: List<Task>? = TasksRepository().defaultSort(response.body()?.taskList)
                it.postValue(taskList)
            } else {
                it.postValue(mutableListOf())
            }
        } catch (e: HttpException) {
            it.postValue(mutableListOf())
        } catch (e: Throwable) {
            it.postValue(mutableListOf())
        }
    }


}