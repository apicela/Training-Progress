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
    var image: String,
    var listOfExercises: List<Exercise>
) : Serializable {
    @Ignore
    constructor(divisionName: String, image: String, listOfExercises: List<Exercise>) :
            this(UUID.randomUUID().toString(), divisionName, image, listOfExercises)
}