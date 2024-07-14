package com.apicela.training.services

import com.apicela.training.HomeActivity
import com.apicela.training.models.Observation

class ObservationService(){
    val db = HomeActivity.database;

    fun addObservation(observation : Observation){
        db.observationDao().insert(observation);
    }
}