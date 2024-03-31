package com.apicela.training.services

import android.content.Context
import com.apicela.training.HomeActivity
import com.apicela.training.data.DataManager
import com.apicela.training.data.Database
import com.apicela.training.models.Workout
import com.apicela.training.preferences.SharedPreferencesHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkoutService(private val db: Database) {
    val divisionService : DivisionService = DivisionService(db)

    suspend fun addWorkout(workoutName: String, descricao: String) {
        val workoutItem = Workout(
            workoutName, descricao, listOf(
                divisionService.createDivision("A","divisionA"),
                divisionService.createDivision("B","divisionA"),
                divisionService.createDivision("C","divisionA"),
            )
        )
        withContext(Dispatchers.IO) {
            db.workoutDao().insert(workoutItem)
        }
    }
}