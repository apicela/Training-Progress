package com.apicela.training.services

import com.apicela.training.data.Database
import com.apicela.training.models.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkoutService(private val db: Database) {
    val divisionService : DivisionService = DivisionService(db)

    suspend fun addWorkout(workoutName: String, descricao: String) {
        val workoutItem = Workout(
            workoutName, descricao, listOf(
                divisionService.createDivision("A"),
                divisionService.createDivision("B"),
                divisionService.createDivision("C"),
            )
        )
        withContext(Dispatchers.IO) {
            db.workoutDao().insert(workoutItem)
        }
    }
}