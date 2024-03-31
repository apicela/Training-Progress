package com.apicela.training.services

import com.apicela.training.data.Database
import com.apicela.training.models.Division
import com.apicela.training.models.Exercise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class DivisionService(private val db: Database){
    fun createDivision( divisionName: String,
                        image: String) : Division {
        val division = Division( divisionName, listOf(), image)
        CoroutineScope(Dispatchers.IO).launch {
            db.divisionDao().insert(division)
        }
        return division
    }

//    fun changeListExercisesOfDivision( divisionID : String,
//                                       newList : List<Exercise>){
//        val division =
//
//    }


}