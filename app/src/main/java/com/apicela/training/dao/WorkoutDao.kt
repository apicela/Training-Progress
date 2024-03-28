package com.apicela.training.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.apicela.training.models.Workout

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workout")
    fun getAllDivisions(): List<Workout>

    @Insert
    fun insert(workout: Workout)
}