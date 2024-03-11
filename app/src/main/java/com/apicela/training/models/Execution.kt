package com.apicela.training.models

import java.util.Date

data class Execution(
    var sets: Integer,
    var repetitions: Integer,
    val exercise: Exercise,
    var date: Date
)
