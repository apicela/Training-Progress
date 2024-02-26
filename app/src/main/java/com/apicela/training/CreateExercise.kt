package com.apicela.training

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.apicela.training.models.Muscles
import com.apicela.training.services.ExerciseService
import com.apicela.training.utils.DataManager
import com.apicela.training.utils.UtilsComponents
import com.google.gson.Gson

class CreateExercise : AppCompatActivity() {
    private lateinit var backButton: Button
    private lateinit var concludeButton: Button
    private lateinit var exerciseService: ExerciseService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_exercise)
        val exerciseName : EditText = findViewById(R.id.exerciseNameText)
        val imageUrl : EditText = findViewById(R.id.imageUrlText)
        val muscleTypeSpinner : Spinner = findViewById(R.id.muscleTypeSpinner)
        exerciseService = ExerciseService(this)
        backButton = findViewById(R.id.back_button)
        concludeButton = findViewById(R.id.concludeButton)


        val items =  Muscles.getAsList()

        // Adaptador para o Spinner
        val adapter = ArrayAdapter(this, R.layout.dropdown_muscle_type, items)

        // Layout do dropdown do Spinner
        adapter.setDropDownViewResource(R.layout.dropdown_muscle_type)

        // Definir o adaptador para o Spinner
        muscleTypeSpinner.adapter = adapter


        concludeButton.setOnClickListener {
            exerciseService.addExerciseToList(exerciseName.text.toString(), "", Muscles.valueOf(UtilsComponents.getSpinnerSelectedItem(muscleTypeSpinner)))
        }

        backButton.setOnClickListener {
            finish()
        }
        }

}