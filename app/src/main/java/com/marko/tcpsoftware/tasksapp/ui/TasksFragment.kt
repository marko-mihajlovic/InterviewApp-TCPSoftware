package com.marko.tcpsoftware.tasksapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.marko.tcpsoftware.tasksapp.adapter.TasksAdapter
import com.marko.tcpsoftware.tasksapp.databinding.TasksFragmentBinding
import com.marko.tcpsoftware.tasksapp.model.Task

class TasksFragment : Fragment() {

    companion object {
        fun newInstance() = TasksFragment()
    }

    private lateinit var binding: TasksFragmentBinding
    private var viewModel: TasksViewModel? = null
    private var newsAdapter: TasksAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TasksFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        confListAndAdapter()
        confViewModel()

    }

    private fun confViewModel() {
        viewModel = ViewModelProvider(this).get(TasksViewModel::class.java)
        viewModel?.taskList?.observe(viewLifecycleOwner, {
            updateUI(it)
        })
    }

    private fun confListAndAdapter() {
        newsAdapter = TasksAdapter()
        binding.listView.adapter = newsAdapter
    }

    private fun updateUI(list: List<Task>?) {
        newsAdapter?.updateTaskList(list)
    }
}