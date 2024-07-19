package com.apicela.training.createActivity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.apicela.training.HomeActivity
import com.apicela.training.R
import com.apicela.training.models.Exercise
import com.apicela.training.models.Muscle
import com.apicela.training.services.ExerciseService
import com.apicela.training.utils.Codes
import com.apicela.training.utils.UtilsComponents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateExercise : AppCompatActivity() {
    private lateinit var backButton: Button
    private lateinit var concludeButton: Button
    private lateinit var exerciseService: ExerciseService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_exercise)
        val exerciseName: EditText = findViewById(R.id.exerciseNameText)
        val imageUrl: EditText = findViewById(R.id.imageUrlText)
        val muscleTypeSpinner: Spinner = findViewById(R.id.muscleTypeSpinner)
        exerciseService = ExerciseService()
        backButton = findViewById(R.id.back_button)
        concludeButton = findViewById(R.id.concludeButton)


        val items = Muscle.getAsListPTBR()

        val adapter = ArrayAdapter(this, R.layout.transparent_layout, items)
        adapter.setDropDownViewResource(R.layout.dropdown_muscle_type)
        muscleTypeSpinner.adapter = adapter


        concludeButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val muscle = (UtilsComponents.getSpinnerSelectedItem(muscleTypeSpinner))
                exerciseService.addExerciseToDatabase(
                    Exercise(
                        exerciseName.text.toString(),
                        imageUrl.text.toString(),
                        Muscle.getMusclePTBRtoENG(muscle)!!
                    )
                )
            }
            val resultIntent = Intent()
            setResult(Codes.RESULT_CODE_EXERCISE_CREATED, resultIntent)
            finish()
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}