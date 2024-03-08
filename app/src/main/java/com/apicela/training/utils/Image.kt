package com.apicela.training.utils

import android.content.Context
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.apicela.training.R
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.ShapeAppearanceModel

class Image {
    companion object {
        fun createCircleImageView(
            context: Context,
            image: String,
            appearanceModel: ShapeAppearanceModel?,
            size : Int
        ) : ShapeableImageView{
            val circleImageView = ShapeableImageView(context)

            val layoutParams = LinearLayout.LayoutParams(
                size,
                size
            )
            layoutParams.marginEnd =
                context.resources.getDimensionPixelSize(R.dimen.dp20)
            layoutParams.marginStart =
                context.resources.getDimensionPixelSize(R.dimen.dp20)
            circleImageView.layoutParams = layoutParams

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
           }
            if (appearanceModel !== null ) circleImageView.shapeAppearanceModel = appearanceModel
            return circleImageView
            }




//        fun createCircleImageView(
//            context: Context,
//            container: Any,
//            appearanceModel: ShapeAppearanceModel?,
//            image: String
//        ) {
//            val circleImageView = ShapeableImageView(context)
//
//            val layoutParams = LinearLayout.LayoutParams(
//                context.resources.getDimensionPixelSize(R.dimen.dp45),
//                context.resources.getDimensionPixelSize(R.dimen.dp45)
//            )
//            layoutParams.marginEnd =
//                context.resources.getDimensionPixelSize(R.dimen.dp20)
//            layoutParams.marginStart =
//                context.resources.getDimensionPixelSize(R.dimen.dp20)
//            circleImageView.layoutParams = layoutParams
//            if(appearanceModel !== null) { circleImageView.shapeAppearanceModel = appearanceModel }
//
//            val resourceId = context.resources.getIdentifier(image, "drawable", context.packageName)
//            // recurso encontrado
//            if (resourceId != 0) {
//                Glide.with(context)
//                    .asBitmap()
//                    .load(resourceId)
//                    .into(circleImageView)
//
//                circleImageView.setOnClickListener {
//                    Glide.with(context)
//                        .load(resourceId)
//                        .into(GifDrawableImageViewTarget(circleImageView, 1))
//                }
////
//            } else {
//                Glide.with(context)
//                    .asBitmap()
//                    .load(image)
//                    .into(circleImageView)
//
//                circleImageView.setOnClickListener {
//                    Glide.with(context)
//                        .load(image)
//                        .into(GifDrawableImageViewTarget(circleImageView, 1))
//                }
////
//            }
//
//
//            // Set src and background using resources from style (for clarity)
//            circleImageView.setBackgroundResource(R.drawable.image_circle_background) // Assuming background is a drawable
//
//            if(container is LinearLayout) container.addView(circleImageView)
//            else if(container is CardView) container.addView(circleImageView)
//
//        }
    }
}