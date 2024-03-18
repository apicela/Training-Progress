package com.apicela.training

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.apicela.training.models.Division
import com.apicela.training.models.Exercise
import com.apicela.training.ui.utils.ViewCreator

class DivisionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_division)
        val container = findViewById<LinearLayout>(R.id.container)
        val bundle = intent.getBundleExtra("list_bundle")
        if (bundle != null) {
            val listOfDivisions: List<Division>? =
                bundle.getSerializable("list_divisions") as? List<Division>
            listOfDivisions?.forEach { division ->
                val item = ViewCreator.createDivisionLine(this, division.divisionName, "division_a", null)
                item.setOnClickListener {
//                    Log.d("divisionActivity", division.divisionName + " started")
                    val intent = Intent(this@DivisionActivity, ExerciseActivity::class.java)
                    val bundle = Bundle()
                    bundle.putSerializable("list_exercises", division.listOfExercises as ArrayList<Exercise>)
                    intent.putExtra("list_bundle", bundle)
                    intent.putExtra("allExercises", false)
                    startActivity(intent)
                }

                container.addView(item)
            }
        }
    }
}