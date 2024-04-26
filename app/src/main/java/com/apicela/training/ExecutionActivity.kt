package com.apicela.training

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

//class ExecutionActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_execution)
//    }
//}

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apicela.training.adapters.ExecutionAdapter
import com.apicela.training.dialog.RegisterExecutionDialog
import com.apicela.training.models.Execution
import com.apicela.training.services.DivisionService
import com.apicela.training.services.ExecutionService
import com.apicela.training.utils.Codes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

class ExecutionActivity : AppCompatActivity() {

    private lateinit var executionAdapter: ExecutionAdapter
    private lateinit var recyclerViewExecutions: RecyclerView // Add this line
    private lateinit var plusButton: ImageButton
    private lateinit var backButton: Button
    private lateinit var executionService : ExecutionService

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("activity", "execution started")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_execution)
        executionService = ExecutionService(HomeActivity.database)
        backButton = findViewById(R.id.back_button)
        recyclerViewExecutions = findViewById(R.id.recyclerViewExecutions)
        plusButton = findViewById(R.id.plus_button)
        val exercise_id = intent.getStringExtra("exercise_id") as String

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

