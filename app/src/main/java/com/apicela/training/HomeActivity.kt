package com.apicela.training

import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.apicela.training.utils.Codes
import com.apicela.training.utils.DataManager
import com.apicela.training.utils.UtilsComponents

class HomeActivity : AppCompatActivity() {

    private lateinit var exercisesButton: ImageButton
    private lateinit var calendarButton: ImageButton
    private lateinit var newWorkoutButton: AppCompatButton
    private lateinit var containerWorkout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        DataManager.initialize(this)
        exercisesButton = findViewById(R.id.exercise_button)
        calendarButton = findViewById(R.id.calendar_button)
        newWorkoutButton = findViewById(R.id.new_workout_button)
        containerWorkout = findViewById(R.id.containerWorkout)

        containerWorkout.addView(UtilsComponents.createCardViewForWorkout(this,"ola", null))
        containerWorkout.addView(UtilsComponents.createCardViewForWorkout(this,"ZUMBA I LOVE", null))

        exercisesButton.setOnClickListener {
            val intent = Intent(this@HomeActivity, ExerciseActivity::class.java)
            startActivity(intent)
        }

        calendarButton.setOnClickListener {
//            val intent = Intent(this@HomeActivity, CalendarActivity::class.java)
                val intent = Intent(this@HomeActivity, DivisionActivity::class.java)

            startActivity(intent)
        }

        newWorkoutButton.setOnClickListener {
            val intent = Intent(this@HomeActivity, CreateWorkout::class.java)
            startActivityForResult(intent, Codes.REQUEST_CODE_CREATE_EXERCISE)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Codes.REQUEST_CODE_CREATE_EXERCISE && resultCode == Codes.RESULT_CODE_EXERCISE_CREATED) {
            recreate() // Isso reiniciará a Activity
        }
    }
}