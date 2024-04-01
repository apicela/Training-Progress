package com.apicela.training.services

import android.util.Log
import com.apicela.training.data.Database
import com.apicela.training.models.Exercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExecutionService(private val db: Database) {


    suspend fun addExerciseToDatabase(exercise: Exercise) {
        withContext(Dispatchers.IO) {
            db.exerciseDao().insert(exercise)
        }
        Log.d("Exercise", "Exercise added to database")
    }
}