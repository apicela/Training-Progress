package com.apicela.training

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
import com.apicela.training.adapters.ExecutionAdapter
import com.apicela.training.dialog.RegisterExecutionDialog
import com.apicela.training.models.Execution
import com.apicela.training.services.ExecutionService
import com.apicela.training.ui.utils.Image
import com.google.android.material.imageview.ShapeableImageView
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Locale

class ExecutionActivity : AppCompatActivity() {

    private lateinit var executionAdapter: ExecutionAdapter
    private lateinit var recyclerViewExecutions: RecyclerView // Add this line
    private lateinit var plusButton: ImageButton
    private lateinit var backButton: Button
    private lateinit var executionService : ExecutionService
    private lateinit var nameText: TextView
    private lateinit var imageExercise: ShapeableImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("activity", "execution started")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_execution)
        executionService = ExecutionService(HomeActivity.database)
        backButton = findViewById(R.id.back_button)
        recyclerViewExecutions = findViewById(R.id.recyclerViewExecutions)
        plusButton = findViewById(R.id.plus_button)
        nameText = findViewById(R.id.name)
        imageExercise = findViewById(R.id.image)


        val exercise_id = intent.getStringExtra("exercise_id") as String
        nameText.text = intent.getStringExtra("exercise_name") as String
        val exercise_image = intent.getStringExtra("exercise_image") as String

       Image.setImageToImageView(this,imageExercise, exercise_image)

        val executionMap = executionListToMap(exercise_id)
        executionAdapter = ExecutionAdapter(executionMap)
        recyclerViewExecutions.layoutManager = LinearLayoutManager(this)
        recyclerViewExecutions.adapter = executionAdapter
        plusButton.setOnClickListener {
            val dialog = RegisterExecutionDialog(exercise_id, this)
            dialog.show(supportFragmentManager, "RegistrarExercicioDialog")
            dialog.onDismissListener = {
                executionAdapter.updateData(executionListToMap(exercise_id))
            }
        }
        backButton.setOnClickListener {
            finish()
        }
    }
    fun executionListToMap(exercise_id : String) : Map<String, List<Execution>>{
        val executionList = runBlocking { executionService.findExecutionsListByExerciseId(exercise_id) }
        return executionList.groupBy { execution ->
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(execution.date)
        }
    }
}

