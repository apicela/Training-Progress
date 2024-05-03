package com.apicela.training.ui.utils

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import com.apicela.training.R


class ViewCreator {

    companion object {
        val defaultParam = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        fun dpToPx(dp: Int, context: Context): Int {
            val density = context.resources.displayMetrics.density
            return (dp * density).toInt()
        }

        fun convertPixelsToDp(px: Float, context: Context): Float {
            val resources = context.resources
            val metrics = resources.displayMetrics
            return px / (metrics.densityDpi / 160f)
        }


        fun createLinearLayoutForDivision(context: Context): LinearLayout {
            // Criar LinearLayout
            val linearLayout = LinearLayout(
                context,
                null,
                R.style.linearLayoutDivision,
                R.style.linearLayoutDivision
            )
            return linearLayout
        }


        fun createDivisionLine(
            context: Context,
            image: String,
            text: String
        ): LinearLayout {
            val linearLayout = createLinearLayoutForDivision(context)
            linearLayout.layoutParams = defaultParam
            val img = ImageHelper.createCircleImageView(
                context,
                image,
                false,
                null,
                context.resources.getDimensionPixelSize(R.dimen.dp50)
            )
            linearLayout.addView(img)
            val text = Text.createTextView(context, text, "TextViewDivision", null)
            linearLayout.addView(text)
            return linearLayout
        }
    }

}