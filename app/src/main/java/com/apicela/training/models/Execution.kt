package com.apicela.training.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Execution(
    @PrimaryKey var id: String,
    var sets: Integer,
    var repetitions: Integer,
    val exercise_id: String,
    var date: Date
)
