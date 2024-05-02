package com.apicela.training.services

import com.apicela.training.data.Database
import com.apicela.training.models.Division
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DivisionService(private val db: Database) {
    fun createDivision(divisionName: String, image: String): Division {
        val division = Division(divisionName, image, listOf())
        CoroutineScope(Dispatchers.IO).launch {
            db.divisionDao().insert(division)
        }
        return division
    }

    suspend fun updateDivisionObject(division: Division) {
        withContext(Dispatchers.IO) {
            db.divisionDao().update(division)
        }
    }

    fun addDivisionToWorkout(division: Division, workout_id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            var workout = db.workoutDao().getWorkoutById(workout_id)
            workout?.listOfDivision = workout!!.listOfDivision + division
            db.workoutDao().update(workout)
        }
    }

    suspend fun getDivisionById(id: String): Division? {
        return withContext(Dispatchers.IO) {
            db.divisionDao().getDivisionById(id)
        }
    }

}