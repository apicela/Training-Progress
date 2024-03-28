package com.apicela.training.models

import java.io.Serializable
import java.util.UUID

data class Division(
    var id : UUID,
    var divisionName: String,
    var listOfExercises: List<Exercise>,
    var image: String
) : Serializable