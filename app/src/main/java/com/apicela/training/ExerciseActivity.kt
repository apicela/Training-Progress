package com.apicela.training


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apicela.training.adapters.ExerciseAdapter
import com.apicela.training.adapters.ExerciseItemAdapter
import com.apicela.training.createActivity.CreateExercise
import com.apicela.training.interfaces.ExerciseAdapterInterface
import com.apicela.training.services.ExerciseService
import com.apicela.training.utils.Codes.Companion.REQUEST_CODE_CREATED
import com.apicela.training.utils.Codes.Companion.RESULT_CODE_EXERCISE_CREATED
import kotlinx.coroutines.runBlocking


class ExerciseActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var exerciseAdapter: RecyclerView.Adapter<*>
    private lateinit var plusButton: ImageButton
    private lateinit var backButton: Button
    private lateinit var editButton: Button

    private val exerciseService: ExerciseService = ExerciseService()
    private var editMode = false;
    private var divisionId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("activity", "exercise started")
        super.onCreate(savedInstanceState)
        divisionId = intent.getStringExtra("division_id")
        bindViews()
        setUpRecyclerView()
        setUpOnClick()
    }

    private fun setUpOnClick() {
        plusButton.setOnClickListener {
            if (divisionId != null) {
                val intent = Intent(this@ExerciseActivity, AddExerciseActivity::class.java)
                intent.putExtra("division_id", divisionId)
                startActivityForResult(intent, REQUEST_CODE_CREATED)
            } else {
                val intent = Intent(this@ExerciseActivity, CreateExercise::class.java)
                startActivityForResult(intent, REQUEST_CODE_CREATED)
            }
        }
        backButton.setOnClickListener {
            finish()
        }

        editButton.setOnClickListener {
            editMode = !editMode
            if (exerciseAdapter is ExerciseAdapterInterface) {
                (exerciseAdapter as ExerciseAdapterInterface).setEditing(editMode)
            }
            plusButton.visibility =
                if (plusButton.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }
    }

    private fun setUpRecyclerView() {
        exerciseAdapter = if (divisionId == null) {
            // non-sense -> se tem divisionId, logo, não tem cardview
            val exerciseListMap = runBlocking { exerciseService.exerciseListToMap() }
            ExerciseAdapter(this, exerciseListMap, null, exerciseService)
        } else ExerciseItemAdapter(this, divisionId)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = exerciseAdapter
        if (exerciseAdapter is ItemTouchHelperAdapter) {
            val callback = ItemMoveCallback(exerciseAdapter as ItemTouchHelperAdapter)
            val touchHelper = ItemTouchHelper(callback)
            touchHelper.attachToRecyclerView(recyclerView)
        }
    }

    private fun bindViews() {
        setContentView(R.layout.activity_exercise)
        // layouts
        plusButton = findViewById(R.id.plus_button)
        backButton = findViewById(R.id.back_button)
        editButton = findViewById(R.id.edit)
        recyclerView = findViewById(R.id.recyclerView)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CREATED && resultCode == RESULT_CODE_EXERCISE_CREATED) {
            if (exerciseAdapter is ExerciseAdapterInterface) {
                (exerciseAdapter as ExerciseAdapterInterface).refreshData()
            }
        }
    }
}