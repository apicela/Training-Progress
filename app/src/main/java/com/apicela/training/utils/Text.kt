package com.apicela.training.utils

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.apicela.training.R
import com.apicela.training.models.Muscles

class Text {

    companion object{
        fun createTextView(
            context: Context,
            text: String,
            tag: Any?
        ) : TextView{
            val textView =
                TextView(context, null, R.style.TextViewCardWorkout, R.style.TextViewCardWorkout)
            textView.text = text
            if (tag is Muscles) {
                textView.tag = tag
            } else if (tag is String) {
                textView.tag = tag
            }
            return textView
        }
    }
}