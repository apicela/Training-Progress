package com.apicela.training.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity
data class Execution(
    @PrimaryKey var id: String,
    var repetitions: Float,
    var kg : Float,
    val exercise_id: String,
    var date: Date
){
    @Ignore
    constructor(repetitions: Float, kg : Float,exercise_id: String, date : Date ) :
            this(UUID.randomUUID().toString(), repetitions, kg, exercise_id, date)
}
