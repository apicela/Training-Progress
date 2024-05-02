package com.apicela.training


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apicela.training.adapters.ExerciseAdapter
import com.apicela.training.interfaces.OnExerciseCheckedChangeListener
import com.apicela.training.models.Division
import com.apicela.training.models.Exercise
import com.apicela.training.services.DivisionService
import com.apicela.training.services.ExerciseService
import com.apicela.training.utils.Codes
import com.apicela.training.utils.Codes.Companion.REQUEST_CODE_CREATED
import com.apicela.training.utils.Codes.Companion.RESULT_CODE_EXERCISE_CREATED
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class AddExerciseActivity : AppCompatActivity(), OnExerciseCheckedChangeListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: ExerciseAdapter
    private lateinit var backButton: Button
    private lateinit var addExerciseToWorkoutButton: AppCompatButton
    private lateinit var exerciseListMap: Map<String, List<Exercise>>
    private lateinit var division: Division
    private var checkedItems: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)
        val exerciseService = ExerciseService(HomeActivity.database)
        val divisionService = DivisionService(HomeActivity.database)
        val division_id = intent.getStringExtra("division_id")
        // layouts
        backButton = findViewById(R.id.back_button)
        addExerciseToWorkoutButton = findViewById(R.id.add_exercise_to_workout)
        CoroutineScope(Dispatchers.IO).launch {
            division = divisionService.getDivisionById(division_id!!)!!
        }
        runBlocking {
            exerciseListMap = exerciseService.exerciseListToMap() ?: emptyMap()
        }

        recyclerView = findViewById(R.id.recyclerView)
        exerciseAdapter = ExerciseAdapter(this, exerciseListMap, exerciseService, this)
        exerciseAdapter.setEditing(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = exerciseAdapter

        backButton.setOnClickListener {
            finish()
        }

        addExerciseToWorkoutButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                if (division != null) {
                    val checkedItems = exerciseAdapter.getSelectedExercises()
                    val newListExercises = (division!!.listOfExercises + checkedItems).distinct()
                    division?.listOfExercises = newListExercises
                    divisionService.updateDivisionObject(division!!)
                }
            }
            val resultIntent = Intent()
            setResult(Codes.RESULT_CODE_EXERCISE_CREATED, resultIntent)
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CREATED && resultCode == RESULT_CODE_EXERCISE_CREATED) {
            recreate() // Isso reiniciará a Activity
        }
    }

    override fun onCheckedItemCountChanged(count: Int) {
        checkedItems = count
        addExerciseToWorkoutButton.text =
            "Adicionar exercícios (${checkedItems})"
    }
}