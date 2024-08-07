package com.apicela.training.services

import com.apicela.training.HomeActivity
import com.apicela.training.data.Database
import com.apicela.training.models.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkoutService() {
    val db = HomeActivity.DATABASE
    val divisionService: DivisionService = DivisionService()

    suspend fun addWorkout(workoutName: String, descricao: String, image: String) {
        val workoutItem = Workout()
        withContext(Dispatchers.IO) {
            db.workoutDao().insert(workoutItem)
        }
        workoutItem.name = workoutName
        workoutItem.description = descricao
        workoutItem.image = image
        workoutItem.listOfDivision = listOf(
            divisionService.createDivision(workoutItem.id, "A", "number_1"),
            divisionService.createDivision(workoutItem.id, "B", "number_2"),
            divisionService.createDivision(workoutItem.id, "C", "number_3"),
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