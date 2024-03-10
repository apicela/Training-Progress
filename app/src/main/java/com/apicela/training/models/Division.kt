package com.apicela.training.models

import java.io.Serializable

data class Division(
    var divisionName: String,
    var listOfExercises: List<Exercise>,
    var image: String
) : Serializable