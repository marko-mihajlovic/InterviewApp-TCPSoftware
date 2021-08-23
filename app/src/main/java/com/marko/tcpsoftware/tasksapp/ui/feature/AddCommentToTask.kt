package com.marko.tcpsoftware.tasksapp.ui.feature

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.marko.tcpsoftware.tasksapp.R
import com.marko.tcpsoftware.tasksapp.databinding.DialogCommentBinding
import com.marko.tcpsoftware.tasksapp.util.toggleKeyboard
import com.marko.tcpsoftware.tasksapp.viewmodels.TasksViewModel

class AddCommentToTask(
    private val activity: Activity,
    private val viewModel: TasksViewModel
) {

    fun askToAddComment() {
        AlertDialog.Builder(activity, R.style.Dialog)
            .setTitle("Add comment?")
            .setMessage("Choose whether to add a comment to this task")
            .setPositiveButton("Yes") { dialog, _ ->
                dialog.cancel()
                addCommentDialog()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

    fun addCommentDialog(){
        val binding = DialogCommentBinding.inflate(activity.layoutInflater)
        toggleKeyboard(activity, binding.commentTxt, true)

        binding.commentTxt.append(viewModel.getSelectedTask()?.comment ?: "")

        AlertDialog.Builder(activity, R.style.Dialog)
            .setView(binding.root)
            .setPositiveButton("Confirm") { dialog, _ ->

                val newComment = binding.commentTxt.text.trim().toString()
                viewModel.selectedTask.postValue(viewModel.getSelectedTask()?.apply { comment = newComment })

                dialog.cancel()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }


}