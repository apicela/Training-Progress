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
        exerciseService = ExerciseService(HomeActivity.database)
        backButton = findViewById(R.id.back_button)
        concludeButton = findViewById(R.id.concludeButton)


        val items = Muscle.getAsListPTBR()

        // Adaptador para o Spinner
        val adapter = ArrayAdapter(this, R.layout.transparent_layout, items)

        // Layout do dropdown do Spinner
        adapter.setDropDownViewResource(R.layout.dropdown_muscle_type)

        // Definir o adaptador para o Spinner
        muscleTypeSpinner.adapter = adapter


        concludeButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
            exerciseService.addExerciseToDatabase(
                Exercise(exerciseName.text.toString(),
                    imageUrl.text.toString(),
                    Muscle.valueOf(UtilsComponents.getSpinnerSelectedItem(muscleTypeSpinner)))
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