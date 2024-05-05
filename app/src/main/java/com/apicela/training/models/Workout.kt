package com.apicela.training.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Workout(
    @PrimaryKey var id: String,
    var workoutName: String,
    var description: String,
    var image: String,
    var listOfDivision: List<Division>
) {
    @Ignore
    constructor() : this(UUID.randomUUID().toString(), "", "", "", emptyList())

}