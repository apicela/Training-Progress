package com.apicela.training

import androidx.appcompat.app.AppCompatActivity
import  com.apicela.training.models.Exercise
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CalendarView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import android.widget.ListView

class MainActivity2 : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        calendarView = findViewById(R.id.calendarView)
        listView = findViewById(R.id.listView)

        // Inicializar a lista de itens
        val items = mutableListOf<Exercise>()

        // Configurar o adaptador da lista
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items.map { it.exerciseName })
        listView.adapter = adapter

        // Configurar o ouvinte de clique no calendário
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val clickedDate = Calendar.getInstance()
            clickedDate.set(year, month, dayOfMonth)

            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val dateString = dateFormat.format(clickedDate.time)
            Log.d("calendar", " dateString "+ dateString.toString())
            // Filtrar a lista de itens com base na data clicada
            val filteredItems = items
            //   .filter { it.date == dateString }
            Log.d("calendar", " filtered items "+filteredItems.toString())
            // Atualizar a lista da interface do usuário
            adapter.clear()
            adapter.addAll(filteredItems.map { it.exerciseName })
        }

        // Configurar o ouvinte de clique na lista (opcional)
        listView.setOnItemClickListener(AdapterView.OnItemClickListener { _, _, position, _ ->
            // Lidar com o clique na lista, se necessário
            // Exemplo: val selectedItem = items[position]
            // TODO: Implemente a lógica de manipulação do clique na lista
        })
    }
}
