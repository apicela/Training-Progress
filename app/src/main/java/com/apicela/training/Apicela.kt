package com.apicela.training

import android.app.Application
import com.apicela.training.data.DataManager
import com.apicela.training.models.Exercise
import com.apicela.training.utils.preferences.SharedPreferencesHelper
import com.apicela.training.services.ExerciseService
import com.apicela.training.ui.activitys.HomeActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Apicela : Application() {
    lateinit var exerciseService: ExerciseService

    override fun onCreate() {
        super.onCreate()
        HomeActivity.DATABASE = DataManager.getDatabase(this)
        exerciseService = ExerciseService()
        setUpFirstStart()

    }

    fun setUpFirstStart() {
        CoroutineScope(Dispatchers.IO).launch {
            val sharedPreferencesHelper = SharedPreferencesHelper()
            sharedPreferencesHelper.initializeOnce(applicationContext)
            val listExercises = exerciseService.getAllExercises()
            val itemsToAdd =
                Exercise.listaExercises.filter { obj -> listExercises.none { it.name == obj.name } }

            itemsToAdd.forEach {
                exerciseService.addExerciseToDatabase(it)
            }
        }

    }
}