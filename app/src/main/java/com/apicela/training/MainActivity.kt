package com.apicela.training

import androidx.appcompat.app.AppCompatActivity
import  com.apicela.training.models.Exercises
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CalendarView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import android.widget.ListView
import java.util.Date

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(R.layout.exercises_adapter)

    }
}
