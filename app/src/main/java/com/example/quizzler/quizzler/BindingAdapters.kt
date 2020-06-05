package com.example.quizzler.quizzler

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.widget.Button
import androidx.databinding.BindingAdapter
import com.example.quizzler.R

@BindingAdapter("colorInBackground")
fun Button.changeBackground(color: Int) {
    color?.let{
        background = when(color){
            0 -> ColorDrawable(resources.getColor(R.color.buttonColorForWrongAnswer))
            1 -> ColorDrawable(resources.getColor(R.color.buttonColorForRightAnswer))
            else -> ColorDrawable(resources.getColor(R.color.buttonColorDefault))
        }
    }
}