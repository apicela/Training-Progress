package com.apicela.training.ui.activitys

//class ExecutionActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_execution)
//    }
//}

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apicela.training.R
import com.apicela.training.adapters.ExecutionAdapter
import com.apicela.training.ui.dialogs.RegisterExecutionDialog
import com.apicela.training.ui.utils.ImageHelper
import com.google.android.material.imageview.ShapeableImageView

class ExecutionActivity : AppCompatActivity() {

    private lateinit var executionAdapter: ExecutionAdapter
    private lateinit var recyclerViewExecutions: RecyclerView // Add this line
    private lateinit var plusButton: ImageButton
    private lateinit var backButton: Button
    private lateinit var edit: Button
    private lateinit var nameText: TextView
    private lateinit var imageExercise: ShapeableImageView
    private lateinit var exerciseId : String
    private lateinit var exerciseImage : String
    var editMode = true;


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("activity", "execution started")
        super.onCreate(savedInstanceState)
        bindView()
        setUpViews()
        setUpRecyclerView()
        setUpOnClick()
    }

    private fun setUpViews() {
        exerciseId = intent.getStringExtra("exercise_id") as String
        nameText.text = intent.getStringExtra("exercise_name") as String
        exerciseImage  = intent.getStringExtra("exercise_image") as String
        ImageHelper.setImage(this, imageExercise, exerciseImage, false)
    }

    private fun setUpOnClick() {
        plusButton.setOnClickListener {
            val dialog = RegisterExecutionDialog(exerciseId, null, this)
            dialog.show(supportFragmentManager, "RegistrarExercicioDialog")
            dialog.onDismissListener = {
                executionAdapter.refreshData(exerciseId)
            }
        }
        backButton.setOnClickListener {
            finish()
        }
        edit.setOnClickListener {
            executionAdapter.setEditing(editMode)
            editMode = !editMode
        }
    }

    private fun setUpRecyclerView() {
        executionAdapter = ExecutionAdapter(this, exerciseId)
        recyclerViewExecutions.layoutManager = LinearLayoutManager(this)
        recyclerViewExecutions.adapter = executionAdapter
    }

    private fun bindView() {
        setContentView(R.layout.activity_execution)
        backButton = findViewById(R.id.back_button)
        edit = findViewById(R.id.edit)
        recyclerViewExecutions = findViewById(R.id.recyclerViewExecutions)
        plusButton = findViewById(R.id.plus_button)
        nameText = findViewById(R.id.name)
        imageExercise = findViewById(R.id.image)
    }
}

