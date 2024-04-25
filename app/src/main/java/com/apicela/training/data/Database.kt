package com.apicela.training.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.apicela.training.converter.Converter
import com.apicela.training.converter.DivisionListConverter
import com.apicela.training.converter.ExerciseListConverter
import com.apicela.training.dao.DivisionDao
import com.apicela.training.dao.ExecutionDao
import com.apicela.training.dao.ExerciseDao
import com.apicela.training.dao.WorkoutDao
import com.apicela.training.models.Division
import com.apicela.training.models.Execution
import com.apicela.training.models.Exercise
import com.apicela.training.models.Workout

@Database(entities = [Division::class, Exercise::class, Workout::class, Execution::class], version = 2, exportSchema = false)
@TypeConverters(
    ExerciseListConverter::class,  DivisionListConverter::class, Converter::class )
abstract class Database : RoomDatabase() {
    abstract fun divisionDao(): DivisionDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun executionDao() : ExecutionDao
}