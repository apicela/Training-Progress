package com.apicela.training.services

import android.util.Log
import com.apicela.training.HomeActivity
import com.apicela.training.data.Database
import com.apicela.training.models.Execution
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.Date

class ExecutionService(private val db: Database) {
    val exerciseService : ExerciseService = ExerciseService(HomeActivity.database)

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
    suspend fun findExecutionsListByDate(date : Date) : List<Execution> {
        date.hours = 0
        date.minutes = 0
        date.seconds = 0
        val timestamp = date.time - (date.time%1000)
        return withContext(Dispatchers.IO) {
            db.executionDao().getAllExecutionFromDate(timestamp)
        }
    }

    suspend fun groupExercisesExecutionByDateIntoString(date : Date) : List<String>{
        val items = runBlocking{ findExecutionsListByDate(date) }
        val listOfExecutions = mutableListOf("");
        val groupedItems = items.groupBy { it.exercise_id }
        for (exerciseId in groupedItems.keys) {
            val exerciseName = exerciseService.getExerciseById(exerciseId).exerciseName
            listOfExecutions.add("${exerciseName} : ${joinExerciseListToString(groupedItems[exerciseId]!!)}")
        }
        return listOfExecutions
    }
    suspend fun getAll() : List<Execution> {
        return withContext(Dispatchers.IO) {
            db.executionDao().getAllExecution()
        }
    }

    fun joinExerciseListToString(list : List<Execution>) : String{
        return  list.joinToString(", ") { "${it.repetitions}x${it.kg}kg"     }
    }

}