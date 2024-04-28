package com.apicela.training

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CalendarView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.apicela.training.services.ExecutionService
import com.apicela.training.services.ExerciseService
import kotlinx.coroutines.runBlocking
import java.util.Calendar
import java.util.Date

class CalendarActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var listView: ListView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        val executionService : ExecutionService = ExecutionService(HomeActivity.database)
        val exerciseService : ExerciseService = ExerciseService(HomeActivity.database)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendar_activity)
        backButton = findViewById(R.id.back_button)

        backButton.setOnClickListener {
            finish()
        }
        calendarView = findViewById(R.id.calendarView)
        listView = findViewById(R.id.listView)


        val listOfExecutions = mutableListOf("");
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listOfExecutions)

        // Configurar o ouvinte de clique no calendário
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            listOfExecutions.clear()
            val clickedDate = Calendar.getInstance()
            clickedDate.set(year, month, dayOfMonth)
            val date : Date = (clickedDate.time)
            val items = runBlocking{ executionService.findExecutionsListByDate(date) }
            val groupedItems = items.groupBy { it.exercise_id }
            for (exerciseId in groupedItems.keys) {
                val exerciseName = runBlocking { exerciseService.getExerciseById(exerciseId).exerciseName }
                listOfExecutions.add("${exerciseName} : ${executionService.joinExerciseListToString(groupedItems[exerciseId]!!)}")
            }
            Log.d("calendar","${listOfExecutions}")
            adapter.clear()
            adapter.addAll(listOfExecutions)
        }
            // Configurar o adaptador da lista
            // Atualizar a lista da interface do usuário

        }


}
