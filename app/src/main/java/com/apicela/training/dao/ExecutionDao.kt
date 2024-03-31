package com.apicela.training.dao

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.apicela.training.models.Execution

interface ExecutionDao {
    @Query("SELECT * FROM execution WHERE date = :date")
    fun getAllExecutionFromDate(date : String): List<Execution>

    @Query("SELECT * FROM Execution WHERE exercise_id = :exerciseId")
    fun getAllExecutionFromExercise(exerciseId: String): List<Execution>

    @Insert
    fun insert(execution: Execution)

    @Update
    fun update(execution: Execution)
}