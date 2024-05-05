package com.apicela.training.services

import com.apicela.training.data.Database
import com.apicela.training.models.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkoutService(private val db: Database) {
    val divisionService: DivisionService = DivisionService(db)

    suspend fun addWorkout(workoutName: String, descricao: String, image: String) {
        val workoutItem = Workout()
        withContext(Dispatchers.IO) {
            db.workoutDao().insert(workoutItem)
        }
        workoutItem.workoutName = workoutName
        workoutItem.description = descricao
        workoutItem.image = image
        workoutItem.listOfDivision = listOf(
            divisionService.createDivision(workoutItem.id, "A", "division_a"),
            divisionService.createDivision(workoutItem.id, "B", "division_b"),
            divisionService.createDivision(workoutItem.id, "C", "division_c"),
        )

        withContext(Dispatchers.IO) {
            db.workoutDao().update(workoutItem)
        }
    }

    suspend fun getAllWorkouts(): List<Workout> {
        return withContext(Dispatchers.IO) {
            db.workoutDao().getAllWorkouts()
        }
    }

    suspend fun getWorkoutById(id: String): Workout {
        return withContext(Dispatchers.IO) {
            db.workoutDao().getWorkoutById(id)
        }
    }

    suspend fun deleteById(id: String) {
        withContext(Dispatchers.IO) {
            db.workoutDao().deleteById(id)
        }
    }

    suspend fun updateWorkout(workout: Workout) {
        withContext(Dispatchers.IO) {
            db.workoutDao().update(workout)
        }
    }


}