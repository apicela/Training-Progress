package com.apicela.training.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.apicela.training.models.Observation

@Dao
interface ObservationDao {
    @Query("SELECT * FROM observation WHERE strftime('%d/%m/%Y', date / 1000, 'unixepoch') =  :date")
    fun getObservationByDate(date: String): Observation?

    @Query("SELECT * FROM observation WHERE date = :date")
    fun getObservationByDate(date: Long): Observation?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(observation: Observation)

    @Update
    fun update(observation: Observation)

}