package com.apicela.training.models

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Exercises(var exerciseName : String, var imageURL : String, var muscleType : Muscles)

{
    companion object {
        val listaExercises: MutableList<Exercises> by lazy { mutableListOf<Exercises>(
            // CHEST
            Exercises("SUPINO RETO", "someUrl1",Muscles.CHEST),

            // BACK
            Exercises("PUXADOR PULLEY", "someUrl2",Muscles.BACK),
            Exercises("REMADA CURVADA", "someUrl3",Muscles.BACK)

        )
        }
    }
}