package com.apicela.training.services

import android.util.Log
import com.apicela.training.data.Database
import com.apicela.training.models.Exercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseService(private val db: Database) {


    suspend fun addExerciseToDatabase(exercise: Exercise) {
        withContext(Dispatchers.IO) {
            db.exerciseDao().insert(exercise)
        }
        Log.d("Exercise", "Exercise added to database")
    }

//    fun addExerciseToDivision(exerciseName: String, image: String, muscleType: Muscle) {
//        val exerciseItem = Exercise(exerciseName, image, muscleType)
//        val lista = DataManager.loadExerciseItems()
//        lista.add(exerciseItem)
//        DataManager.saveExerciseItems(lista)
//        Log.d("Exercise", "Exercise added to workout")
//    }

}