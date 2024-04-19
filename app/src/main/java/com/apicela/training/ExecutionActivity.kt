package com.apicela.training

import android.os.Bundle
import android.util.Log
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
import com.apicela.training.models.Execution
import java.sql.Date

class ExecutionActivity : AppCompatActivity() {

    private lateinit var executionAdapter: ExecutionAdapter
    private lateinit var recyclerViewExecutions: RecyclerView // Add this line

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("activity", "execution started")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_execution)
        recyclerViewExecutions = findViewById(R.id.recyclerViewExecutions)

//        val executionList =
//
//        val groupedExecutions = executionList.groupBy { it.date }
//
//        val executionGroups = groupedExecutions.entries.map { it.key to it.value }
//
//        executionAdapter = ExecutionAdapter(this, executionGroups)
        //      recyclerViewExecutions.layoutManager = LinearLayoutManager(this)
        //       recyclerViewExecutions.adapter = executionAdapter
    }
}
