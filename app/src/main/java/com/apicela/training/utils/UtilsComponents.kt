package com.apicela.training.utils

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.ui.unit.dp
import androidx.core.view.marginStart
import com.apicela.training.R
import de.hdodenhof.circleimageview.CircleImageView


class UtilsComponents {

    companion object {

        fun checkChildsPedidoActivity(mainLinearLayout: LinearLayout) : ViewGroup.LayoutParams?{
            for (i in 0 until mainLinearLayout.childCount) {
                val childView = mainLinearLayout.getChildAt(i)
                 if(childView is CircleImageView) {
                     return childView.layoutParams
                }
            }
            return null
        }

        fun dpToPx(dp: Int, context: Context): Int {
            val density = context.resources.displayMetrics.density
            return (dp * density).toInt()
        }

        fun convertPixelsToDp(px: Float, context: Context): Float {
            val resources = context.resources
            val metrics = resources.displayMetrics
            return px / (metrics.densityDpi / 160f)
        }

        fun createLinearLayoutForExercise(context: Context): LinearLayout {
            // Criar LinearLayout
            val linearLayout = LinearLayout(context,null , R.style.linearLayoutExercise, R.style.linearLayoutExercise)
            return linearLayout
        }

        fun createTextView(context: Context, text: String, container : LinearLayout) {
            val textView = TextView(context, null, R.style.TextViewExercise, R.style.TextViewExercise)
            textView.text = text
            container.addView(textView)
        }

        fun createCircleImageView(context: Context, container: LinearLayout) {
            val circleImageView = CircleImageView(context, null, R.style.ImageViewExercise )
            val layoutParams = LinearLayout.LayoutParams(
                context.resources.getDimensionPixelSize(R.dimen.circle_image_width),
                context.resources.getDimensionPixelSize(R.dimen.circle_image_width)
            )
            layoutParams.marginEnd = context.resources.getDimensionPixelSize(R.dimen.circle_image_margin_start)
            layoutParams.marginStart = context.resources.getDimensionPixelSize(R.dimen.circle_image_margin_start)
            circleImageView.layoutParams = layoutParams

            // Set src and background using resources from style (for clarity)
            circleImageView.setImageResource(R.drawable.muscle_group_abs)
            circleImageView.setBackgroundResource(R.drawable.image_circle_background) // Assuming background is a drawable

            container.addView(circleImageView)
        }

        fun createExerciseLine(context: Context, text: String) : LinearLayout{
            val linearLayout = createLinearLayoutForExercise(context)
             createCircleImageView(context, linearLayout)
            createTextView(context, text, linearLayout)
            return linearLayout
        }
    }
}