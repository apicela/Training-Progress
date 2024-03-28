package com.apicela.training.interfaces

import androidx.room.Database
import androidx.room.RoomDatabase
import com.apicela.training.dao.DivisionDao
import com.apicela.training.dao.ExerciseDao
import com.apicela.training.dao.WorkoutDao
import com.apicela.training.models.Division
import com.apicela.training.models.Exercise
import com.apicela.training.models.Workout

@Database(entities = [Division::class, Exercise::class, Workout::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun divisionDao(): DivisionDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
}