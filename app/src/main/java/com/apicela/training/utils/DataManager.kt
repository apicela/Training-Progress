package com.apicela.training.utils

import android.content.Context
import android.content.SharedPreferences
import android.os.Parcelable
import com.apicela.training.models.Exercise
import com.apicela.training.models.Workout
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.io.Serializable


object DataManager {
    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences
    private val gson = Gson()

    fun initialize(context: Context) {
        this.context = context.applicationContext
        sharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE)
    }

    fun saveExerciseItems(items: List<Exercise>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(items)
        editor.putString("exercise_items_list", json)
        editor.apply()
    }

    fun loadExerciseItems(): MutableList<Exercise> {
        val json = sharedPreferences.getString("exercise_items_list", "")
        return if (json.isNullOrEmpty()) {
            Exercise.listaExercises
        } else {
            val exeriseType = object : TypeToken<List<Exercise>>() {}.type
            gson.fromJson(json, exeriseType)
        }
    }

    fun saveWorkout(workout: List<String>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(workout)
        editor.putString("workout_list", json)
        editor.apply()
    }

    fun loadWorkout(): MutableList<Workout> {
        val json = sharedPreferences.getString("workout_list", "")
        return if (json.isNullOrEmpty()) {
            Workout.listaExercises
        } else {
            val workoutType = object : TypeToken<List<Workout>>() {}.type
            gson.fromJson(json, workoutType)
        }
    }
}

// Criar uma instância do DataManager
//val dataManager = DataManager(context)
//
//// Salvando e carregando lista de itens
//val itemList = listOf(Item("Item 1"), Item("Item 2"), Item("Item 3"))
//dataManager.saveItems(itemList)
//val loadedItemList = dataManager.loadItems()
//
//// Salvando e carregando lista de nomes
//val nameList = listOf("Nome 1", "Nome 2", "Nome 3")
//dataManager.saveNames(nameList)
//val loadedNameList = dataManager.loadNames()