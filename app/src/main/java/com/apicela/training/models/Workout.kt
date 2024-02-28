package com.apicela.training.models

data class Workout(var workoutName : String, var descricao : String,var listOfDivision: List<Division>){
    companion object {
        val listaExercises: MutableList<Workout> by lazy { mutableListOf<Workout>() }
    }
}
