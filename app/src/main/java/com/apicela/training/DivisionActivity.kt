package com.apicela.training

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.apicela.training.createActivity.CreateDivision
import com.apicela.training.data.Database
import com.apicela.training.models.Division
import com.apicela.training.ui.utils.ViewCreator
import com.apicela.training.utils.Codes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DivisionActivity : AppCompatActivity() {
    private lateinit var backButton: Button
    private lateinit var plusButton: ImageButton
    private lateinit var db : Database
    private lateinit var descriptionText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("activity", "division started")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_division)
        val container = findViewById<LinearLayout>(R.id.container)
        plusButton = findViewById(R.id.plus_button)
        backButton = findViewById(R.id.back_button)
        descriptionText = findViewById(R.id.description_text)
        val workout_id = intent.getStringExtra("workout_id")


        CoroutineScope(Dispatchers.IO).launch {
            db = HomeActivity.database
            val workout =  db.workoutDao().getWorkoutById(workout_id!!)
            descriptionText.text = workout?.description
            val listOfDivisions: List<Division>? = workout?.listOfDivision
            withContext(Dispatchers.Main) {
                listOfDivisions?.forEach { division ->
                    val item = ViewCreator.createDivisionLine(applicationContext, division.divisionName)
                    item.setOnClickListener {
                        val intent = Intent(this@DivisionActivity, ExerciseActivity::class.java)
                        intent.putExtra("division_id", division.id)
                        intent.putExtra("isDivision", true)
                        startActivity(intent)
                    }
                    container.addView(item)
                }
            }}

        backButton.setOnClickListener {
            finish()
        }

        plusButton.setOnClickListener {
                val intent = Intent(this@DivisionActivity, CreateDivision::class.java)
                intent.putExtra("workout_id", workout_id)
                startActivityForResult(intent, Codes.REQUEST_CODE_CREATED)
            }
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Codes.REQUEST_CODE_CREATED) {
            recreate() // Isso reiniciará a Activity
        }
    }
}