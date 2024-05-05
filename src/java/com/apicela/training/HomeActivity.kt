package com.apicela.training

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apicela.training.adapters.WorkoutAdapter
import com.apicela.training.createActivity.CreateWorkout
import com.apicela.training.data.DataManager
import com.apicela.training.data.Database
import com.apicela.training.models.Exercise
import com.apicela.training.preferences.SharedPreferencesHelper
import com.apicela.training.services.ExerciseService
import com.apicela.training.services.WorkoutService
import com.apicela.training.utils.Codes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HomeActivity : AppCompatActivity() {

    companion object {
        lateinit var database: Database
    }

    private lateinit var workoutAdapter: WorkoutAdapter
    private lateinit var exercisesButton: ImageButton
    private lateinit var calendarButton: ImageButton
    private lateinit var newWorkoutButton: AppCompatButton
    private lateinit var recyclerView: RecyclerView // Add this line

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        database = DataManager.getDatabase(applicationContext)
        val workoutService: WorkoutService = WorkoutService(database)
        val exerciseService: ExerciseService = ExerciseService(database)

        val workouts = runBlocking { workoutService.getAllWorkouts() }

        recyclerView = findViewById(R.id.recyclerView)
        workoutAdapter = WorkoutAdapter(this, workouts, workoutService)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = workoutAdapter
        CoroutineScope(Dispatchers.IO).launch {
            val sharedPreferencesHelper = SharedPreferencesHelper()
            sharedPreferencesHelper.initializeOnce(applicationContext, database)
            val listExercises = runBlocking { exerciseService.getAllExercises() }
            val itemsToAdd =
                Exercise.listaExercises.filter { obj -> listExercises.none { it.name == obj.name } }

            itemsToAdd.forEach {
                exerciseService.addExerciseToDatabase(it)
            }
        }


        exercisesButton = findViewById(R.id.exercise_button)
        calendarButton = findViewById(R.id.calendar_button)
        newWorkoutButton = findViewById(R.id.new_workout_button)



        exercisesButton.setOnClickListener {
            val intent = Intent(this@HomeActivity, ExerciseActivity::class.java)
            startActivity(intent)
        }

        calendarButton.setOnClickListener {
            val intent = Intent(this@HomeActivity, CalendarActivity::class.java)
            startActivity(intent)
        }

        newWorkoutButton.setOnClickListener {
            val intent = Intent(this@HomeActivity, CreateWorkout::class.java)
            startActivityForResult(intent, Codes.REQUEST_CODE_CREATED)
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Codes.REQUEST_CODE_CREATED && resultCode == Codes.RESULT_CODE_EXERCISE_CREATED) {
            recreate() // Isso reiniciará a Activity
        }
    }
}