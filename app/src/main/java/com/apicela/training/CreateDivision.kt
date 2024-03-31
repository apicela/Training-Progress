package com.apicela.training


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
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
        divisionService = DivisionService(HomeActivity.database)
        backButton = findViewById(R.id.back_button)
        concludeButton = findViewById(R.id.concludeButton)
        val workout_id = intent.getStringExtra("workout_id")

        concludeButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val division = divisionService.createDivision(divisionName.text.toString())
                divisionService.addDivisionToWorkout(division, workout_id!!)
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