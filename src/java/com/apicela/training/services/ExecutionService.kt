package com.apicela.training.services

import android.util.Log
import com.apicela.training.data.Database
import com.apicela.training.models.Execution
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ExecutionService(private val db: Database) {
    val exerciseService: ExerciseService = ExerciseService(db)

    suspend fun addExecutionToDatabase(execution: Execution) {
        withContext(Dispatchers.IO) {
            db.executionDao().insert(execution)
        }
        Log.d("Exercise", "Exercise added to database")
    }

    suspend fun deleteById(id: String) {
        withContext(Dispatchers.IO) {
            db.executionDao().deleteById(id)
        }
    }

    suspend fun findExecutionsListByExerciseId(exerciseId: String): List<Execution> {
        return withContext(Dispatchers.IO) {
            db.executionDao().getAllExecutionFromExercise(exerciseId)
        }
    }

    suspend fun findExecutionsListByDate(date: Date): List<Execution> {
        date.hours = 0
        date.minutes = 0
        date.seconds = 0
        val timestamp = date.time - (date.time % 1000)
        return withContext(Dispatchers.IO) {
            db.executionDao().getAllExecutionFromDate(timestamp)
        }
    }

    suspend fun groupExercisesExecutionByDateIntoString(date: Date): List<String> {
        val items = runBlocking { findExecutionsListByDate(date) }
        val listOfExecutions = mutableListOf("");
        val groupedItems = items.groupBy { it.exercise_id }
        for (exerciseId in groupedItems.keys) {
            val exerciseName = exerciseService.getExerciseById(exerciseId).name
            listOfExecutions.add(
                "${exerciseName.lowercase()} : ${
                    joinExerciseListToString(
                        groupedItems[exerciseId]!!
                    )
                }"
            )
        }
        return listOfExecutions
    }

    fun executionListToMap(exercise_id: String): Map<String, List<Execution>> {
        val executionList = runBlocking { findExecutionsListByExerciseId(exercise_id) }
        return executionList.groupBy { execution ->
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(execution.date)
        }
    }

    suspend fun getAll(): List<Execution> {
        return withContext(Dispatchers.IO) {
            db.executionDao().getAllExecution()
        }
    }

    suspend fun updateExecutionObject(execution: Execution) {
        withContext(Dispatchers.IO) {
            db.executionDao().update(execution)
        }
    }

    suspend fun getLastInsertedExecution(id: String): Execution? {
        Log.d("execution", "get last Inserted ")
        return withContext(Dispatchers.IO) {
            db.executionDao().getLastInsertedExecution(id)
        }
    }

    suspend fun getExecutionById(id: String): Execution? {
        return withContext(Dispatchers.IO) {
            db.executionDao().getExecutionById(id)
        }
    }

    fun joinExerciseListToString(list: List<Execution>): String {
        return list.joinToString(", ") { "${it.repetitions}x${it.kg}kg" }
    }

}