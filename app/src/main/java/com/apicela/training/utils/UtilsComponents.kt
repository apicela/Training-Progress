package com.apicela.training.utils

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import com.apicela.training.R
import com.apicela.training.models.Muscles
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.ShapeAppearanceModel
import de.hdodenhof.circleimageview.CircleImageView


class UtilsComponents {

    companion object {

        fun getSpinnerSelectedItem(spinner: Spinner): String {
            val selectedIndex = spinner.selectedItemPosition
            val spinnerItem = spinner.getItemAtPosition(selectedIndex).toString()
            return spinnerItem
        }

        fun checkChildsPedidoActivity(mainLinearLayout: LinearLayout): ViewGroup.LayoutParams? {
            for (i in 0 until mainLinearLayout.childCount) {
                val childView = mainLinearLayout.getChildAt(i)
                if (childView is CircleImageView) {
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
            val linearLayout = LinearLayout(
                context,
                null,
                R.style.linearLayoutExercise,
                R.style.linearLayoutExercise
            )
            return linearLayout
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

        fun createTextView(
            context: Context,
            text: String,
            container: LinearLayout,
            muscleType: Muscles?
        ) {
            val textView =
                TextView(context, null, R.style.TextViewExercise, R.style.TextViewExercise)
            textView.text = text
            if(muscleType !== null) { textView.tag = muscleType }
            container.addView(textView)
        }

        var url =
            "https://mir-s3-cdn-cf.behance.net/project_modules/hd/5eeea355389655.59822ff824b72.gif"

        fun createCircleImageView(
            context: Context,
            container: LinearLayout,
            appearanceModel: ShapeAppearanceModel?,
            image: String
        ) {
            val circleImageView = ShapeableImageView(context)
            val layoutParams = LinearLayout.LayoutParams(
                context.resources.getDimensionPixelSize(R.dimen.circle_image_width),
                context.resources.getDimensionPixelSize(R.dimen.circle_image_width)
            )
            layoutParams.marginEnd =
                context.resources.getDimensionPixelSize(R.dimen.circle_image_margin_start)
            layoutParams.marginStart =
                context.resources.getDimensionPixelSize(R.dimen.circle_image_margin_start)
            circleImageView.layoutParams = layoutParams
            if(appearanceModel !== null) { circleImageView.shapeAppearanceModel = appearanceModel }

            val resourceId = context.resources.getIdentifier(image, "drawable", context.packageName)
            // recurso encontrado
            if (resourceId != 0) {
                Glide.with(context)
                    .asBitmap()
                    .load(resourceId)
                    .into(circleImageView)

                circleImageView.setOnClickListener {
                    Glide.with(context)
                        .load(resourceId)
                        .into(GifDrawableImageViewTarget(circleImageView, 1))
                }
//
            } else {
                Glide.with(context)
                    .asBitmap()
                    .load(image)
                    .into(circleImageView)

                circleImageView.setOnClickListener {
                    Glide.with(context)
                        .load(image)
                        .into(GifDrawableImageViewTarget(circleImageView, 1))
                }
//
            }


            // Set src and background using resources from style (for clarity)
            circleImageView.setBackgroundResource(R.drawable.image_circle_background) // Assuming background is a drawable

            container.addView(circleImageView)
        }

        fun createExerciseLine(
            context: Context,
            text: String,
            muscleType: Muscles,
            appearanceModel: ShapeAppearanceModel,
            image: String
        ): LinearLayout {
            val linearLayout = createLinearLayoutForExercise(context)
            createCircleImageView(context, linearLayout, appearanceModel, image)
            createTextView(context, text, linearLayout, muscleType)
            return linearLayout
        }

        fun createDivisionLine(
            context: Context,
            text: String,
            image: String
        ): LinearLayout {
            val linearLayout = createLinearLayoutForDivision(context)
            createCircleImageView(context, linearLayout, null, image)
            createTextView(context, text, linearLayout, null)
            return linearLayout
        }
    }
}