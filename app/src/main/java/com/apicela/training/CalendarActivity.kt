package com.apicela.training

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CalendarView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.apicela.training.services.ExecutionService
import kotlinx.coroutines.runBlocking
import java.util.Calendar
import java.util.Date

class CalendarActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var listView: ListView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        val executionService: ExecutionService = ExecutionService(HomeActivity.database)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity)
        backButton = findViewById(R.id.back_button)

        backButton.setOnClickListener {
            finish()
        }
        calendarView = findViewById(R.id.calendarView)
        listView = findViewById(R.id.listView)

        val today = Date()
        val listOfExecutions =
            runBlocking { executionService.groupExercisesExecutionByDateIntoString(today) }
        val adapter = ArrayAdapter(this, R.layout.simple_list_item, listOfExecutions)
        listView.adapter = adapter

        // Configurar o ouvinte de clique no calendário
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            adapter.clear()
            val clickedDate = Calendar.getInstance()
            clickedDate.set(year, month, dayOfMonth)
            val date: Date = (clickedDate.time)
            val listOfExecutions =
                runBlocking { executionService.groupExercisesExecutionByDateIntoString(date) }
            adapter.addAll(listOfExecutions)
            adapter.notifyDataSetChanged()
        }
    }


}
