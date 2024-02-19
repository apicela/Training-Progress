package com.apicela.training

import androidx.appcompat.app.AppCompatActivity
import  com.apicela.training.models.Exercise
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

import com.apicela.training.models.Muscles
import com.apicela.training.utils.UtilsComponents

class ExerciseActivity : AppCompatActivity() {

    val exercises = Exercise.listaExercises
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        val backLayout = findViewById<LinearLayout>(R.id.backLayout)
        val chestLayout = findViewById<LinearLayout>(R.id.chestLayout)
        val shoulderLayout = findViewById<LinearLayout>(R.id.shoulderLayout)
        val othersLayout = findViewById<LinearLayout>(R.id.others)
        val tricepsLayout = findViewById<LinearLayout>(R.id.tricepsLayout)
        val bicepsLayout = findViewById<LinearLayout>(R.id.bicepsLayout)
        val quadricepsLayout = findViewById<LinearLayout>(R.id.quadricepsLayout)
        val hamstringLayout = findViewById<LinearLayout>(R.id.hamstringLayout)
        val glutesCalvesLayout = findViewById<LinearLayout>(R.id.glutes_calvesLayout)
        val absLayout = findViewById<LinearLayout>(R.id.absLayout)

        for (exercise in exercises) {
            val exerciseItem = UtilsComponents.createExerciseLine(this, exercise.exerciseName)
            when (exercise.muscleType) {
                Muscles.BACK -> backLayout.addView(exerciseItem)
                Muscles.CHEST -> chestLayout.addView(exerciseItem)
                Muscles.SHOULDER -> shoulderLayout.addView(exerciseItem)
                Muscles.TRICEPS -> tricepsLayout.addView(exerciseItem)
                Muscles.BICEPS -> bicepsLayout.addView(exerciseItem)
                Muscles.QUADRICEPS -> quadricepsLayout.addView(exerciseItem)
                Muscles.HAMSTRING -> hamstringLayout.addView(exerciseItem)
                Muscles.CALVES -> glutesCalvesLayout.addView(exerciseItem)
                Muscles.GLUTES -> glutesCalvesLayout.addView(exerciseItem)
                Muscles.ABDOMINAL  -> absLayout.addView(exerciseItem)
                else  -> othersLayout.addView(exerciseItem)

            }
        }
    }



}
