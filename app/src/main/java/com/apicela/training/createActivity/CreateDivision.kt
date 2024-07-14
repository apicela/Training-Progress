package com.apicela.training.createActivity


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.apicela.training.HomeActivity
import com.apicela.training.R
import com.apicela.training.services.DivisionService
import com.apicela.training.utils.Codes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateDivision : AppCompatActivity() {
    private lateinit var backButton: Button
    private lateinit var concludeButton: Button
    private lateinit var divisionService: DivisionService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_division)
        val divisionName: EditText = findViewById(R.id.divisionNameText)
        val image: EditText = findViewById(R.id.imageUrlText)

        divisionService = DivisionService(HomeActivity.DATABASE)
        backButton = findViewById(R.id.back_button)
        concludeButton = findViewById(R.id.concludeButton)
        val workoutId = intent.getStringExtra("workout_id")

        concludeButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val division = divisionService.createDivision(
                    workoutId!!,
                    divisionName.text.toString(),
                    image.text.toString()
                )
                divisionService.addDivisionToWorkout(division, workoutId!!)
            }
            val resultIntent = Intent()
            setResult(Codes.REQUEST_CODE_CREATED, resultIntent)
            finish()
        }

        backButton.setOnClickListener {
            finish()
        }


    }
}