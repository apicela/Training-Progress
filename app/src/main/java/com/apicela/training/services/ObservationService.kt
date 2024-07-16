package com.apicela.training.services

import com.apicela.training.HomeActivity
import com.apicela.training.models.Observation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ObservationService(){
    val db = HomeActivity.DATABASE;

    fun addObservation(observation : Observation){
        db.observationDao().insert(observation);
    }

    fun update(observation: Observation){
        db.observationDao().update(observation)
    }
    // @Pattern dd/MM/yyyy
    suspend fun getObservationByDate(date : String) : Observation? {
        return withContext(Dispatchers.IO) { db.observationDao().getObservationByDate(date) }
    }
}