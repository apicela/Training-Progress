package com.apicela.training.ui.utils

import android.content.Context
import android.widget.ImageView
import android.widget.LinearLayout
import com.apicela.training.R
import com.apicela.training.utils.GifDrawableImageViewTarget
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.ShapeAppearanceModel

class ImageHelper {
    companion object {

        fun setImage(
            context: Context,
            view: ImageView,
            image: String,
            isGif: Boolean
        ) {
            val resourceId = context.resources.getIdentifier(image, "drawable", context.packageName)
            // recurso encontrado
            if (resourceId != 0) {
                Glide.with(context)
                    .asBitmap()
                    .load(resourceId)
                    .into(view)
                if (isGif) {
                    view.setOnClickListener {
                        Glide.with(context)
                            .load(resourceId)
                            .into(GifDrawableImageViewTarget(view, 1))
                    }
                }
//
            } else {
                Glide.with(context)
                    .asBitmap()
                    .load(image)
                    .into(view)
                if (isGif) {

                    view.setOnClickListener {
                        Glide.with(context)
                            .load(image)
                            .into(GifDrawableImageViewTarget(view, 1))
                    }
                }
            }
        }

        fun createCircleImageView(
            context: Context,
            image: String,
            isGif: Boolean,
            appearanceModel: ShapeAppearanceModel?,
            size: Int
        ): ShapeableImageView {
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
                if (isGif) {
                    circleImageView.setOnClickListener {
                        Glide.with(context)
                            .load(resourceId)
                            .into(GifDrawableImageViewTarget(circleImageView, 1))
                    }
                }
//
            } else {
                Glide.with(context)
                    .asBitmap()
                    .load(image)
                    .into(circleImageView)
                if (isGif) {

                    circleImageView.setOnClickListener {
                        Glide.with(context)
                            .load(image)
                            .into(GifDrawableImageViewTarget(circleImageView, 1))
                    }
                }
            }
            if (appearanceModel !== null) circleImageView.shapeAppearanceModel = appearanceModel
            return circleImageView
        }


        fun setImageToImageView(
            context: Context,
            imageView: ShapeableImageView,
            image: String
        ) {
            val resourceId = context.resources.getIdentifier(image, "drawable", context.packageName)
            // recurso encontrado
            if (resourceId != 0) {
                Glide.with(context)
                    .asBitmap()
                    .load(resourceId)
                    .into(imageView)

                imageView.setOnClickListener {
                    Glide.with(context)
                        .load(resourceId)
                        .into(GifDrawableImageViewTarget(imageView, 1))
                }
//
            }
        }

    }
}