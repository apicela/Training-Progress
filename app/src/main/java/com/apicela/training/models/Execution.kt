package com.apicela.training.models

import androidx.room.Entity
import java.util.Date

@Entity
data class Execution(
    var sets: Integer,
    var repetitions: Integer,
    val exercise_id: String,
    var date: Date
)
