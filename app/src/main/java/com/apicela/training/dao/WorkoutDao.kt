package com.apicela.training.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.apicela.training.models.Division
import com.apicela.training.models.Workout

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workout")
    fun getAllWorkouts(): List<Workout>

    @Query("SELECT * FROM workout WHERE id = :workoutId")
    fun getWorkoutById(workoutId: String): Workout?
    @Insert
    fun insert(workout: Workout)
}