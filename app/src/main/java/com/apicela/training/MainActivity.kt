package com.apicela.training

import androidx.appcompat.app.AppCompatActivity
import  com.apicela.training.models.Exercises
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CalendarView
import android.widget.LinearLayout
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import android.widget.ListView
import com.apicela.training.utils.UtilsComponents
import java.util.Date

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val linearLayout : LinearLayout = findViewById(R.id.defaultLayout)
//        setContentView(R.layout.exercises_adapter)

        val itemsContainer : LinearLayout = findViewById(R.id.teste)
        val x = UtilsComponents.createExerciseLine(this, "SUPINO RETO")
        Log.d("chield", "haha")
        UtilsComponents.checkChildsPedidoActivity(x)
        itemsContainer.addView(x)

    }
}
