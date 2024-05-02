package com.apicela.training.ui.utils

import android.content.Context
import android.widget.TextView
import com.apicela.training.R
import com.apicela.training.models.Muscle

class Text {

    companion object {
        fun getStyle(style: String): Int {
            return when (style) {
                "cardViewWorkout" -> R.style.TextViewCardWorkout
                "TextViewDivision" -> R.style.TextViewDivision
                else -> 0
            }
        }

        fun createTextView(
            context: Context,
            text: String,
            style: String,
            tag: Any?
        ): TextView {

            val textView =
                TextView(context, null, getStyle(style), getStyle(style))
            textView.text = text
            if (tag is Muscle) {
                textView.tag = tag
            } else if (tag is String) {
                textView.tag = tag
            }
            return textView
        }
    }
}