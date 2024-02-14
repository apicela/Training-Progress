package com.apicela.training.activitys

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.apicela.training.R
import com.bumptech.glide.Glide

class ExerciseActivity : AppCompatActivity() {


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.exercises_adapter)

//            ImageView chestImageView = findViewById(R.id.chest_image)
//            Glide.with(this).asGif().load(R.drawable.seu_gif).into(chestImageView);
        }


}