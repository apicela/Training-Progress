package com.apicela.training.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.UUID

@Entity
data class Division(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    var divisionName: String,
    var listOfExercises: List<Exercise>,
    var image: String
) : Serializable