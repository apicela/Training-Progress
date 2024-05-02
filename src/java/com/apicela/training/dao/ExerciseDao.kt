package com.apicela.training.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.apicela.training.models.Exercise

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise")
    fun getAllExercises(): List<Exercise>

    @Query("SELECT * FROM exercise WHERE id = :id")
    fun getExerciseById(id: String): Exercise

    @Insert
    fun insert(exercise: Exercise)
}

