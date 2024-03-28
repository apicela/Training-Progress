package com.apicela.training.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Workout(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    var workoutName: String,
    var descricao: String,
    var listOfDivision: List<Division>
) {
    companion object {
        val listaExercises: MutableList<Workout> by lazy {
            mutableListOf<Workout>(
//            Workout("Treino 01", "descricao", listOf())
            )
        }
    }
}
