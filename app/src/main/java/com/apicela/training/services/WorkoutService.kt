package com.apicela.training.services

import android.content.Context
import com.apicela.training.models.Division
import com.apicela.training.models.Workout
import com.apicela.training.utils.DataManager

class WorkoutService(context: Context) {
    fun addWorkoutToList(workoutName: String, descricao: String) {
        val workoutItem = Workout(workoutName, descricao, listOf(
            Division("A", listOf()),
            Division("B", listOf()),
            Division("C", listOf())
            ))
        val lista = DataManager.loadWorkoutItems()
        lista.add(workoutItem)
        DataManager.saveWorkoutItems(lista)
    }
}