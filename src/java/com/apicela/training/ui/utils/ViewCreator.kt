package com.apicela.training.ui.utils

import android.content.Context
import android.view.ViewGroup


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

    }
}