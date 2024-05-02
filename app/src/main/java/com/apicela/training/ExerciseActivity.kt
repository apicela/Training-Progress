package com.apicela.training

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apicela.training.adapters.ExerciseAdapter
import com.apicela.training.createActivity.CreateExercise
import com.apicela.training.models.Exercise
import com.apicela.training.services.DivisionService
import com.apicela.training.services.ExerciseService
import com.apicela.training.utils.Codes.Companion.REQUEST_CODE_CREATED
import com.apicela.training.utils.Codes.Companion.RESULT_CODE_EXERCISE_CREATED
import com.apicela.training.utils.UtilsComponents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class ExerciseActivity : AppCompatActivity() {

    private lateinit var exerciseListMap: Map<String,List<Exercise>>
    private lateinit var exerciseService : ExerciseService
    private lateinit var recyclerView : RecyclerView
    private lateinit var exerciseAdapter : ExerciseAdapter
    private lateinit var plusButton: ImageButton
    private lateinit var backButton: Button
    private lateinit var editButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("activity", "exercise started")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        exerciseService = ExerciseService(HomeActivity.database)
        val listOfCheckBox: MutableList<CheckBox> = mutableListOf()
        var checkBoxVisible = false;
        // layouts
        plusButton = findViewById(R.id.plus_button)
        backButton = findViewById(R.id.back_button)
        editButton = findViewById(R.id.edit)

        val isDivision = intent.getBooleanExtra("isDivision", false)
        val division_id = intent.getStringExtra("division_id")

        runBlocking{
            exerciseListMap =
                if (isDivision) {
                    exerciseService.exerciseListToMap(division_id!!) ?: emptyMap()
                } else{
                exerciseService.exerciseListToMap()!!
            }
        }
            recyclerView = findViewById(R.id.recyclerView)

            exerciseAdapter = ExerciseAdapter(this,exerciseListMap, exerciseService)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = exerciseAdapter


        plusButton.setOnClickListener {
            if(isDivision){
                val intent = Intent(this@ExerciseActivity, AddExerciseActivity::class.java)
                intent.putExtra("division_id", division_id)
                startActivityForResult(intent, REQUEST_CODE_CREATED)
            } else{
                val intent = Intent(this@ExerciseActivity, CreateExercise::class.java)
                startActivityForResult(intent, REQUEST_CODE_CREATED)
            }
        }
        backButton.setOnClickListener {
            finish()
        }

        editButton.setOnClickListener{
            UtilsComponents.turnListOfViewVisible(listOfCheckBox, checkBoxVisible)
            checkBoxVisible = !checkBoxVisible;
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CREATED && resultCode == RESULT_CODE_EXERCISE_CREATED) {
            recreate() // Isso reiniciará a Activity
        }
    }
}