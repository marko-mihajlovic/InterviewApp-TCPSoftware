package com.marko.tcpsoftware.tasksapp.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity


fun setTextColor(context: Context?, view: TextView, @ColorRes color : Int){
    if(context==null)
        return

    view.setTextColor(context.getColor(color))
}

fun setImage(context: Context?, view: ImageView, @DrawableRes img : Int){
    if(context==null)
        return

    view.setImageResource(img)
}

fun toggleKeyboard(activity: Activity, view : View?, visible : Boolean){
    if(visible){
        view?.post{
            view.requestFocus()
            val inputMethodManager = activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }else{
        val imm = activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.window.decorView.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
}