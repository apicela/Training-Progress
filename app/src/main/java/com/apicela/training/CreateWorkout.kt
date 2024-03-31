package com.apicela.training

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.apicela.training.services.WorkoutService
import com.apicela.training.utils.Codes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateWorkout : AppCompatActivity() {
    private lateinit var backButton: Button
    private lateinit var concludeButton: Button
    private lateinit var workoutService: WorkoutService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_workout)
        val workouteName: EditText = findViewById(R.id.workoutName)
        val descricaoText: EditText = findViewById(R.id.descricaoText)
        workoutService = WorkoutService(HomeActivity.database)
        backButton = findViewById(R.id.back_button)
        concludeButton = findViewById(R.id.concludeButton)


        concludeButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
            workoutService.addWorkout(
                workouteName.text.toString(),
                descricaoText.text.toString()
            )
            val resultIntent = Intent()
            setResult(Codes.RESULT_CODE_EXERCISE_CREATED, resultIntent)
            finish()
        }
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}