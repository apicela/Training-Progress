package com.apicela.training.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.apicela.training.models.Exercise
import com.apicela.training.models.Workout
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson


object DataManager {
    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    fun initialize(context: Context) {
        DataManager.context = context.applicationContext
        sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE)
    }

    fun saveExerciseItems(items: List<Exercise>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(items)
        editor.putString("exercise_items_list", json)
        editor.apply()
        Log.d("teste", "exercise saved")
    }

    fun loadExerciseItems(): MutableList<Exercise> {
        val json = sharedPreferences.getString("exercise_items_list", "")
        return if (json.isNullOrEmpty()) {
            Exercise.listaExercises
        } else {
            val exeriseType = object : TypeToken<List<Exercise>>() {}.type
            gson.fromJson(json, exeriseType)
        }
        Log.d("teste", "exercise list loaded")
    }

    fun saveWorkoutItems(workout: List<Workout>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(workout)
        editor.putString("workout_list", json)
        editor.apply()
    }

    fun loadWorkoutItems(): MutableList<Workout> {
        val json = sharedPreferences.getString("workout_list", "")
        return if (json.isNullOrEmpty()) {
            Workout.listaExercises
        } else {
            val workoutType = object : TypeToken<List<Workout>>() {}.type
            gson.fromJson(json, workoutType)
        }
    }
}
