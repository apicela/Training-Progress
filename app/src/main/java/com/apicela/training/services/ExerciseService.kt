package com.apicela.training.services

import android.util.Log
import com.apicela.training.data.Database
import com.apicela.training.models.Execution
import com.apicela.training.models.Exercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

class ExerciseService(private val db: Database) {
    val divisionService =  DivisionService(db)


    suspend fun addExerciseToDatabase(exercise: Exercise) {
        withContext(Dispatchers.IO) {
            db.exerciseDao().insert(exercise)
        }
        Log.d("Exercise", "Exercise added to database")
    }

    suspend fun getExerciseById(id : String) : Exercise{
       return withContext(Dispatchers.IO) {
             db.exerciseDao().getExerciseById(id)
        }
    }

    suspend fun getAllExercises() : List<Exercise>{
        return withContext(Dispatchers.IO) {
            db.exerciseDao().getAllExercises()
        }    }

    suspend fun exerciseListToMap(division_id : String? = null) : Map<String, List<Exercise>>?{
        val exerciseList = if(division_id == null) {
            runBlocking { getAllExercises() }
        } else {
            val division = divisionService.getDivisionById(division_id)
             division?.listOfExercises
        }
        if(exerciseList != null){
            return exerciseList.groupBy { exercise ->
                exercise.muscleType.toString()
            }
        }
        return null
    }



//    fun addExerciseToDivision(exerciseName: String, image: String, muscleType: Muscle) {
//        val exerciseItem = Exercise(exerciseName, image, muscleType)
//        val lista = DataManager.loadExerciseItems()
//        lista.add(exerciseItem)
//        DataManager.saveExerciseItems(lista)
//        Log.d("Exercise", "Exercise added to workout")
//    }

}