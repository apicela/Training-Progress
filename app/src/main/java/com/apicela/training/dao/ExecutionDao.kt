package com.apicela.training.dao

import android.util.Log
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.apicela.training.models.Execution
import java.util.Date


@Dao
interface ExecutionDao {

    @Query("SELECT * FROM Execution")
    fun getAllExecution(): List<Execution>
    @Query("SELECT * FROM execution WHERE date = :date")
    fun getAllExecutionFromDate(date : Long): List<Execution>

    @Query("SELECT * FROM Execution WHERE exercise_id = :exerciseId")
    fun getAllExecutionFromExercise(exerciseId: String): List<Execution>

    @Insert
    fun insert(execution: Execution)

    @Update
    fun update(execution: Execution)
}