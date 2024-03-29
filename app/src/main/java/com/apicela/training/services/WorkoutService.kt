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
                DivisionService.createDivision("A","divisionA"),
                DivisionService.createDivision("B","divisionA"),
                DivisionService.createDivision("C","divisionA"),
            )
        )
//        val lista = DataManager.loadWorkoutItems()
//        lista.add(workoutItem)
//        DataManager.saveWorkoutItems(lista)
    }
}