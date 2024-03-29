package com.apicela.training.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.UUID

@Entity
data class Division(
    @PrimaryKey var id: String,
    var divisionName: String,
    var listOfExercises: List<Exercise>,
    var image: String
) : Serializable{
    @Ignore
        constructor(divisionName: String, listOfExercises: List<Exercise>, image: String) :
                this(UUID.randomUUID().toString(), divisionName, listOfExercises, image)
    }