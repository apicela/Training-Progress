package com.apicela.training.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.apicela.training.models.Observation

@Dao
interface ObservationDao {
    @Query("SELECT * FROM observation WHERE strftime('%d/%m/%Y', date / 1000, 'unixepoch') =  :date")
    fun getObservationByDate(date: String): String
    @Insert
    fun insert(observation : Observation)

}