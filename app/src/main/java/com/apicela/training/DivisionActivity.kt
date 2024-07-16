package com.apicela.training

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apicela.training.adapters.DivisionAdapter
import com.apicela.training.createActivity.CreateDivision
import com.apicela.training.services.WorkoutService
import com.apicela.training.utils.Codes
import kotlinx.coroutines.runBlocking

class DivisionActivity : AppCompatActivity() {
    private lateinit var backButton: Button
    private lateinit var editButton: Button
    private lateinit var plusButton: ImageButton
    private lateinit var descriptionText: TextView
    private lateinit var recyclerView: RecyclerView // Add this line
    private lateinit var adapter: DivisionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("activity", "division started")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_division)
        val workoutService = WorkoutService(HomeActivity.DATABASE)
        plusButton = findViewById(R.id.plus_button)
        backButton = findViewById(R.id.back_button)
        editButton = findViewById(R.id.edit)
        var editMode = false;

        descriptionText = findViewById(R.id.description_text)
        val workoutId = intent.getStringExtra("workout_id")
        val workoutDescription =
            runBlocking { workoutService.getWorkoutById(workoutId!!).description }
        descriptionText.text = workoutDescription

        recyclerView = findViewById(R.id.recyclerView)
        adapter = DivisionAdapter(this, workoutId!!)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        backButton.setOnClickListener {
            finish()
        }

        editButton.setOnClickListener {
            editMode = !editMode
            adapter.setEditing(editMode)
            plusButton.visibility =
                if (plusButton.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }

        plusButton.setOnClickListener {
            val intent = Intent(this@DivisionActivity, CreateDivision::class.java)
            intent.putExtra("workout_id", workoutId)
            startActivityForResult(intent, Codes.REQUEST_CODE_CREATED)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Codes.REQUEST_CODE_CREATED) {
            recreate() // Isso reiniciará a Activity
        }
    }
}