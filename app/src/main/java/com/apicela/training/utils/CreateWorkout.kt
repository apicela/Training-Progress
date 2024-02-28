package com.apicela.training.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.apicela.training.R
import com.apicela.training.models.Muscles
import com.apicela.training.services.ExerciseService
import com.apicela.training.services.WorkoutService

class CreateWorkout : AppCompatActivity() {
    private lateinit var backButton: Button
    private lateinit var concludeButton: Button
    private lateinit var workoutService: WorkoutService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_workout)
        val workouteName : EditText = findViewById(R.id.workoutName)
        val descricaoText : EditText = findViewById(R.id.descricaoText)
        workoutService = WorkoutService(this)
        backButton = findViewById(R.id.back_button)
        concludeButton = findViewById(R.id.concludeButton)


        concludeButton.setOnClickListener {
            workoutService.addWorkoutToList(workouteName.text.toString(),  descricaoText.text.toString())
            val resultIntent = Intent()
            setResult(Codes.RESULT_CODE_EXERCISE_CREATED, resultIntent)
            finish()
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}