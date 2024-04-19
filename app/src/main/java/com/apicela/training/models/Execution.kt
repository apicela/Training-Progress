package com.apicela.training.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity
data class Execution(
    @PrimaryKey var id: String,
    var repetitions: Integer,
    val exercise_id: String,
    var date: Date
){
    @Ignore
    constructor(repetitions: Integer, exercise_id: String, date : Date ) :
            this(UUID.randomUUID().toString(), repetitions, exercise_id, date)
}
