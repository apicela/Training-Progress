package com.apicela.training

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.apicela.training.models.Division
import com.apicela.training.models.Exercise
import com.apicela.training.ui.utils.ViewCreator

class DivisionActivity : AppCompatActivity() {
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_division)
        val container = findViewById<LinearLayout>(R.id.container)
        backButton = findViewById(R.id.back_button)
        val bundle = intent.getBundleExtra("list_bundle")
        val workout_id = intent.getStringExtra("workout_id")
        Log.d("teste", "workout     id: ${workout_id!!}")
        if (bundle != null) {
            val listOfDivisions: List<Division>? =
                bundle.getSerializable("list_divisions") as? List<Division>
            listOfDivisions?.forEach { division ->
                val item = ViewCreator.createDivisionLine(this, division.divisionName, "division_a", null)
                item.setOnClickListener {
                    val intent = Intent(this@DivisionActivity, ExerciseActivity::class.java)
                    intent.putExtra("division_id", division.id)
                    intent.putExtra("allExercises", false)
                    startActivity(intent)
                }

                container.addView(item)
            }
        }
        backButton.setOnClickListener {
            finish()
        }
    }


}