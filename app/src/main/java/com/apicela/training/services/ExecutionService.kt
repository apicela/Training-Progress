package com.apicela.training.services

import android.util.Log
import com.apicela.training.data.Database
import com.apicela.training.models.Division
import com.apicela.training.models.Execution
import com.apicela.training.models.Exercise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExecutionService(private val db: Database) {


    suspend fun addExecutionToDatabase(execution: Execution) {
        withContext(Dispatchers.IO) {
            db.executionDao().insert(execution)
        }
        Log.d("Exercise", "Exercise added to database")
    }

     suspend fun findExecutionsListByExerciseId( exerciseId: String) : List<Execution> {
         return withContext(Dispatchers.IO) {
             db.executionDao().getAllExecutionFromExercise(exerciseId)
         }
    }
}