package com.apicela.training

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.apicela.training.createActivity.CreateWorkout
import com.apicela.training.data.DataManager
import com.apicela.training.data.Database
import com.apicela.training.dialog.DeleteItemDialog
import com.apicela.training.preferences.SharedPreferencesHelper
import com.apicela.training.services.WorkoutService
import com.apicela.training.ui.utils.ViewCreator
import com.apicela.training.utils.Codes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable.isCancelled
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity() {

    companion object {
        lateinit var database: Database
    }
    private lateinit var exercisesButton: ImageButton
    private lateinit var calendarButton: ImageButton
    private lateinit var newWorkoutButton: AppCompatButton
    private lateinit var containerWorkout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        CoroutineScope(Dispatchers.IO).launch {
            database = DataManager.getDatabase(applicationContext)
            val workoutService : WorkoutService = WorkoutService(database)
            val sharedPreferencesHelper = SharedPreferencesHelper()
            sharedPreferencesHelper.initializeOnce(applicationContext, database)
            val divisions = workoutService.getAllWorkouts()
            withContext(Dispatchers.Main) {
                divisions.forEach { workout ->
                    val cardWorkout = ViewCreator.createCardViewForWorkout(applicationContext, workout.workoutName, workout.workoutName, workout.image)
                    cardWorkout.setOnClickListener {
                        val intent = Intent(this@HomeActivity, DivisionActivity::class.java)
                        intent.putExtra("workout_id", workout.id)
                        startActivity(intent)
                    }
                    cardWorkout.setOnLongClickListener{
                        val dialog = DeleteItemDialog("HAHAHA")
                        dialog.show(supportFragmentManager, "DeleteItemDialog") // Exibe o DialogFragment

                        dialog.onDismissListener = { // Configura o listener para saber da dismiss do diálogo
                            val confirmDelete = dialog.confirmed // Verifica se o diálogo foi cancelado (clique em "Cancelar")
                            if (confirmDelete) {
                                runBlocking { workoutService.deleteById(workout.id)}
                            }
                        }
                        true
                    }
                    containerWorkout.addView(cardWorkout)
                }
            }
        }

        exercisesButton = findViewById(R.id.exercise_button)
        calendarButton = findViewById(R.id.calendar_button)
        newWorkoutButton = findViewById(R.id.new_workout_button)
        containerWorkout = findViewById(R.id.containerWorkout)



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