package com.marko.tcpsoftware.tasksapp.util

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes


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