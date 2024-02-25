package com.apicela.training.utils

import android.content.Context
import android.content.SharedPreferences
import com.apicela.training.models.Exercise
import com.apicela.training.models.Workout
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class DataManager(private val context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveExerciseItems(items: List<Exercise>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(items)
        editor.putString("exercise_items_list", json)
        editor.apply()
    }

    fun loadExerciseItems(): List<Exercise> {
        val json = sharedPreferences.getString("exercise_items_list", "")
        return if (json.isNullOrEmpty()) {
            listOf()
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

    fun loadWorkout(): List<Workout> {
        val json = sharedPreferences.getString("workout_list", "")
        return if (json.isNullOrEmpty()) {
            listOf()
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