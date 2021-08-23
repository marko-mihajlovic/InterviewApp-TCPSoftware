package com.marko.tcpsoftware.tasksapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.marko.tcpsoftware.tasksapp.R
import com.marko.tcpsoftware.tasksapp.databinding.TaskDetailsFragmentBinding
import com.marko.tcpsoftware.tasksapp.ui.feature.AddCommentToTask
import com.marko.tcpsoftware.tasksapp.util.*
import com.marko.tcpsoftware.tasksapp.viewmodels.TasksViewModel
import java.util.*

class TaskDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = TaskDetailsFragment()
    }

    private lateinit var binding: TaskDetailsFragmentBinding
    private val viewModel : TasksViewModel by activityViewModels()
    private lateinit var addCommentHelper : AddCommentToTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = TaskDetailsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addCommentHelper = AddCommentToTask(requireActivity(), viewModel)
        updateUI()
        listeners()

    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(){
        binding.taskTitleTxt.text = viewModel.getSelectedTask()?.title
        binding.dueDateValueTxt.text = viewModel.getSelectedTask()?.getDueDateFormatted()
        binding.daysLeftValueTxt.text = getDateDiff(Calendar.getInstance().time, viewModel.getSelectedTask()?.dueDate).toString()
        binding.descTxt.text = viewModel.getSelectedTask()?.description
        binding.commentTxt.text = "Your comment: ${viewModel.getSelectedTask()?.comment ?: "-"}"

        toggleStatusUI()
    }

    private fun listeners(){
        viewModel.selectedTask.observe(viewLifecycleOwner, {
            updateUI()
        })

        binding.resolveBtn.setOnClickListener{
            viewModel.updateStatus(STATUS_RESOLVED)
            addCommentHelper.askToAddComment()
        }

        binding.canNotResolveBtn.setOnClickListener{
            viewModel.updateStatus(STATUS_CANNOT_RESOLVE)
            addCommentHelper.askToAddComment()
        }

        binding.commentTxt.setOnClickListener{
            addCommentHelper.addCommentDialog()
        }

        //test revert
        /*binding.statusImg.setOnClickListener{
            viewModel.updateStatus(STATUS_UNRESOLVED)
        }*/
    }


    private fun toggleStatusUI(){
        when(viewModel.getSelectedTask()?.status){
            STATUS_RESOLVED -> {
                toggleStatusTxt(R.string.resolved, R.color.green)
                toggleTaskValueTextColor(R.color.green)
                toggleWhetherAnswered(true)

                setImage(context, binding.statusImg, R.drawable.sign_resolved)
            }
            STATUS_CANNOT_RESOLVE-> {
                toggleStatusTxt(R.string.unresolved, R.color.red)
                toggleTaskValueTextColor(R.color.red)
                toggleWhetherAnswered(true)

                setImage(context, binding.statusImg, R.drawable.unresolved_sign)
            }
            else -> { //STATUS_UNRESOLVED
                toggleStatusTxt(R.string.unresolved, R.color.yellowMiddle)
                toggleTaskValueTextColor(R.color.red)
                toggleWhetherAnswered(false)
            }
        }
    }

    private fun toggleStatusTxt(@StringRes stringRes: Int, @ColorRes color : Int){
        binding.statusTxt.setText(stringRes)
        setTextColor(context, binding.statusTxt, color)
    }

    private fun toggleTaskValueTextColor(@ColorRes color : Int){
        setTextColor(context, binding.taskTitleTxt, color)
        setTextColor(context, binding.dueDateValueTxt, color)
        setTextColor(context, binding.daysLeftValueTxt, color)
    }


    /**
     * if user answered hide buttons and show status image
     */
    private fun toggleWhetherAnswered(answered: Boolean){
        binding.resolveBtn.visibility = if(answered) GONE else VISIBLE
        binding.canNotResolveBtn.visibility = if(answered) GONE else VISIBLE
        binding.statusImg.visibility = if(answered) VISIBLE else GONE
        binding.commentTxt.visibility = if(answered) VISIBLE else GONE
    }

}