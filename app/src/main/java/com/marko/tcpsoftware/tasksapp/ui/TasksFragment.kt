package com.marko.tcpsoftware.tasksapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.marko.tcpsoftware.tasksapp.adapter.TasksAdapter
import com.marko.tcpsoftware.tasksapp.databinding.TasksFragmentBinding
import com.marko.tcpsoftware.tasksapp.interfaces.OpenTaskDetailsListener
import com.marko.tcpsoftware.tasksapp.model.Task
import com.marko.tcpsoftware.tasksapp.util.dateMinusOneDay
import com.marko.tcpsoftware.tasksapp.util.datePlusOneDay
import com.marko.tcpsoftware.tasksapp.util.getTodayDate
import com.marko.tcpsoftware.tasksapp.viewmodels.TasksViewModel

class TasksFragment : Fragment() {

    companion object { fun newInstance() = TasksFragment() }

    enum class VISIBLE_LAYOUT {
        LIST, NO_TASKS, LOADING
    }

    private lateinit var binding: TasksFragmentBinding
    private val viewModel: TasksViewModel by activityViewModels()
    private var newsAdapter: TasksAdapter? = null
    private var visibleLayout : VISIBLE_LAYOUT = VISIBLE_LAYOUT.LOADING
    private var areButtonsEnabled : Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = TasksFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedTask.postValue(null)

        toggleVisibleLayout()
        confListAndAdapter()
        confViewModel()
        onClickListeners()

    }

    private fun confViewModel() {
        viewModel.getTasksForSelectedDay().observe(viewLifecycleOwner, {
            updateUI(it)
        })

        viewModel.titleDay.observe(viewLifecycleOwner,{
            binding.titleTxt.text = it
            binding.noTasksTxt.text = "No tasks for $it"
        })
    }

    private fun confListAndAdapter() {
        newsAdapter = TasksAdapter(viewModel, requireActivity() as OpenTaskDetailsListener)
        binding.listView.adapter = newsAdapter
    }

    private fun updateUI(list: List<Task>?) {
        newsAdapter?.updateTaskList(list)
        areButtonsEnabled = true

        visibleLayout = if(list.isNullOrEmpty()) VISIBLE_LAYOUT.NO_TASKS else VISIBLE_LAYOUT.LIST
        toggleVisibleLayout()
    }


    private fun toggleVisibleLayout() {
        binding.noTasksLayout.visibility = if (visibleLayout== VISIBLE_LAYOUT.NO_TASKS) View.VISIBLE else View.GONE
        binding.listView.visibility = if (visibleLayout== VISIBLE_LAYOUT.LIST) View.VISIBLE else View.GONE
        binding.msgTxt.visibility = if (visibleLayout== VISIBLE_LAYOUT.LOADING) View.VISIBLE else View.GONE

    }

    private fun onClickListeners(){
        binding.leftArrowBtn.setOnClickListener{
            if(areButtonsEnabled) {
                viewModel.updateSelectedDateAndList(dateMinusOneDay(viewModel.selectedDate))
            }
        }

        binding.rightArrowBtn.setOnClickListener{
            if(areButtonsEnabled) {
                viewModel.updateSelectedDateAndList(datePlusOneDay(viewModel.selectedDate))
            }
        }

        binding.titleTxt.setOnClickListener{
            if(areButtonsEnabled) {
                viewModel.updateSelectedDateAndList(getTodayDate())
            }
        }
    }
}