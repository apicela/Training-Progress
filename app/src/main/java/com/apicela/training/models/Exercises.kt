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
            Exercises("REMADA CURVADA", "someUrl3",Muscles.BACK),

            // SHOULDER
            Exercises("LEVANTAMENTO LATERAL", "someUrl3",Muscles.SHOULDER),

            // TRICEPS

            // BICEPS
            Exercises("ROSCA MARTELO", "someUrl3",Muscles.BICEPS),
            Exercises("ROSCA DIRETA", "someUrl3",Muscles.BICEPS),
            Exercises("ROSCA DIRETA NO PULLEY", "someUrl3",Muscles.BICEPS),

            // QUADRICEPS
            Exercises("AGACHAMENTO", "someUrl3",Muscles.QUADRICEPS),
            Exercises("LEVANTAMENTO TERRA", "someUrl3",Muscles.QUADRICEPS),
            Exercises("LEG PRESS", "someUrl3",Muscles.QUADRICEPS),


            // HARMSTRING
            Exercises("STIFF", "someUrl3",Muscles.QUADRICEPS),
            Exercises("MESA FLEXORA", "someUrl3",Muscles.QUADRICEPS),

            // GLUTES / CALVES
            Exercises("PANTURRILHA EM PÉ UNILATERAL", "someUrl3",Muscles.CALVES),
            Exercises("GÊMEOS SENTADO", "someUrl3",Muscles.CALVES),


            // ABS
            Exercises("PRANCHA", "someUrl3",Muscles.ABDOMINAL),
            Exercises("ABDOMINAL INFRA", "someUrl3",Muscles.ABDOMINAL),

            // OTHERS
            Exercises("CORRIDA", "someUrl3",Muscles.OTHER),
            Exercises("NATAÇÃO", "someUrl3",Muscles.OTHER)

            )
        }
    }
}