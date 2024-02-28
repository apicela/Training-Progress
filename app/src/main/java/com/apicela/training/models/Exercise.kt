package com.apicela.training.models

import java.io.Serializable

data class Exercise(var exerciseName: String, var image: String, var muscleType: Muscles) :
    Serializable {
    companion object {
        val listaExercises: MutableList<Exercise> by lazy {
            mutableListOf<Exercise>(
                // CHEST
                Exercise("SUPINO RETO COM HALTERES", "supino_reto_halter", Muscles.CHEST),
                Exercise("SUPINO RETO COM BARRA", "supino_reto_barra", Muscles.CHEST),
                Exercise("SUPINO INCLINADO COM HALTERES", "supino_inclinado_halter", Muscles.CHEST),
                Exercise("SUPINO INCLINADO COM BARRA", "supino_inclinado_barra", Muscles.CHEST),
//            Exercise("CRUCIFIXO RETO", "someUrl1",Muscles.CHEST),
//            Exercise("VOADOR PEITORAL", "someUrl1",Muscles.CHEST),
//            Exercise("FLEXÃO DE BRAÇOS", "someUrl1",Muscles.CHEST),
                Exercise("MERGULHO EM PARALELAS", "dip", Muscles.CHEST),
//            Exercise("SUPINO MÁQUINA", "someUrl1",Muscles.CHEST),
                // BACK
                Exercise("PUXADOR PULLEY", "puxador_pulley", Muscles.BACK),
                Exercise("REMADA CURVADA", "remada_curvada", Muscles.BACK),

                // SHOULDER
                Exercise("LEVANTAMENTO LATERAL", "levantamento_lateral", Muscles.SHOULDER),

                // TRICEPS
                Exercise("TRÍCEPS CORDA", "triceps_corda", Muscles.TRICEPS),
                Exercise("TRÍCEPS BARRA RETA", "triceps_pulley", Muscles.TRICEPS),
                Exercise("TRÍCEPS TESTA", "triceps_testa", Muscles.TRICEPS),
                // BICEPS
                Exercise("ROSCA MARTELO", "rosca_martelo", Muscles.BICEPS),
                Exercise("ROSCA DIRETA COM A BARRA", "rosca_direta_barra", Muscles.BICEPS),
                Exercise("ROSCA DIRETA NO PULLEY", "rosca_direta_pulley", Muscles.BICEPS),

                // QUADRICEPS
                Exercise("AGACHAMENTO", "squat", Muscles.QUADRICEPS),
                Exercise("LEVANTAMENTO TERRA", "deadlift", Muscles.QUADRICEPS),
//            Exercise("LEG PRESS", "someUrl3",Muscles.QUADRICEPS),


                // HARMSTRING
                Exercise("STIFF", "stiff", Muscles.HAMSTRING),
                Exercise("MESA FLEXORA", "mesa_flexora", Muscles.HAMSTRING),

                // GLUTES / CALVES
                Exercise("PANTURRILHA EM PÉ", "panturrilha_em_pe", Muscles.CALVES),
                Exercise("GÊMEOS SENTADO", "gemeos_sentado", Muscles.CALVES),


                // ABS
                Exercise("PRANCHA", "plank", Muscles.ABDOMINAL),
//            Exercise("ABDOMINAL INFRA", "someUrl3",Muscles.ABDOMINAL),

                // OTHERS
                Exercise("CORRIDA", "someUrl3", Muscles.OTHER),
                Exercise("BICICLETA", "someUrl3", Muscles.OTHER),
                Exercise("ESCADA", "someUrl3", Muscles.OTHER),
                Exercise("NATAÇÃO", "someUrl3", Muscles.OTHER)

            )
        }
    }
}