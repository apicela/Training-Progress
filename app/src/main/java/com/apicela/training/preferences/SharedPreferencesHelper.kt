package com.apicela.training.preferences

import android.content.Context
import com.apicela.training.data.Database
import com.apicela.training.models.Exercise
import com.apicela.training.services.ExerciseService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SharedPreferencesHelper() {
    private val PREF_NAME = "user_prefs"
    private val PREF_INITIALIZED = "started23DB"

    suspend fun initializeOnce(context: Context, db : Database) {
        withContext(Dispatchers.IO) {
            val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val initialized = prefs.getBoolean(PREF_INITIALIZED, false)
            if (!initialized) {
                val exerciseService: ExerciseService = ExerciseService(db)
                Exercise.listaExercises.forEach {
                    exerciseService.addExerciseToDatabase(it)
                }

                // Mark as initialized
                prefs.edit().putBoolean(PREF_INITIALIZED, true).apply()
            }
        }
    }
}