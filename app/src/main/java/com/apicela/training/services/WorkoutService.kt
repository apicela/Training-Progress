package com.apicela.training.services

import com.apicela.training.data.Database
import com.apicela.training.models.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkoutService(private val db: Database) {
    val divisionService : DivisionService = DivisionService(db)

    suspend fun addWorkout(workoutName: String, descricao: String, image : String) {
        val workoutItem = Workout(
            workoutName, descricao, image, listOf(
                divisionService.createDivision("A", "division_a"),
                divisionService.createDivision("B","division_b"),
                divisionService.createDivision("C","division_c"),
            )
        )
        withContext(Dispatchers.IO) {
            db.workoutDao().insert(workoutItem)
        }
    }

    suspend fun getAllWorkouts() : List<Workout>{
        return  withContext(Dispatchers.IO) {
            db.workoutDao().getAllWorkouts()
        }
    }

    suspend fun deleteById(id : String){
         withContext(Dispatchers.IO) {
            db.workoutDao().deleteById(id)
        }    }
}