package com.apicela.training.services

import android.content.Context
import com.apicela.training.models.Exercise
import com.apicela.training.models.Muscles
import com.apicela.training.utils.DataManager

    class ExerciseService(context : Context) {

    fun addExerciseToList(exerciseName : String,  image : String, muscleType : Muscles) {
        val exerciseItem = Exercise(exerciseName, image, muscleType)
        val lista = DataManager.loadExerciseItems();
        lista.add(exerciseItem)
        DataManager.saveExerciseItems(lista)
    }
}