package com.apicela.training.ui.utils

import android.content.Context
import android.graphics.Color
import android.widget.TextView
import com.apicela.training.R
import com.apicela.training.models.Muscle

class Text {

    companion object {
        fun createTextView(
            context: Context,
            text: String,
            tag: Any?
        ): TextView {
            val textView =
                TextView(context, null, R.style.TextViewCardWorkout, R.style.TextViewCardWorkout)
            textView.text = text
            textView.setTextColor(Color.WHITE)
            if (tag is Muscle) {
                textView.tag = tag
            } else if (tag is String) {
                textView.tag = tag
            }
            return textView
        }
    }
}