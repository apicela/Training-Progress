package com.apicela.training.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Workout(
    @PrimaryKey var id: String,
    var workoutName: String,
    var descricao: String,
    var listOfDivision: List<Division>
) {
    @Ignore
    constructor( workoutName: String,  descricao: String,  listOfDivision: List<Division>) :
            this(UUID.randomUUID().toString(), workoutName, descricao, listOfDivision)
    companion object {
        val listaExercises: MutableList<Workout> by lazy {
            mutableListOf<Workout>(
//            Workout("Treino 01", "descricao", listOf())
            )
        }
    }
}