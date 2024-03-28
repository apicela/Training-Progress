package com.apicela.training.services

import android.content.Context
import com.apicela.training.data.DataManager
import com.apicela.training.models.Division
import com.apicela.training.models.Workout

class WorkoutService(context: Context) {
    val divisionService : DivisionService = DivisionService()
    fun addWorkoutToList(workoutName: String, descricao: String) {
        val workoutItem = Workout(
            workoutName, descricao, listOf(
                divisionService.createDivision("A","divisionA"),
                divisionService.createDivision("B","divisionA"),
                divisionService.createDivision("C","divisionA"),
            )
        )
        val lista = DataManager.loadWorkoutItems()
        lista.add(workoutItem)
        DataManager.saveWorkoutItems(lista)
    }
}