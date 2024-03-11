package com.apicela.training.models

import java.io.Serializable

data class Exercise(var exerciseName: String, var image: String, var muscleType: Muscle) :
    Serializable {
    companion object {
        val listaExercises: MutableList<Exercise> by lazy {
            mutableListOf<Exercise>(
                // CHEST
                Exercise("SUPINO RETO COM HALTERES", "supino_reto_halter", Muscle.CHEST),
                Exercise("SUPINO RETO COM BARRA", "supino_reto_barra", Muscle.CHEST),
                Exercise("SUPINO INCLINADO COM HALTERES", "supino_inclinado_halter", Muscle.CHEST),
                Exercise("SUPINO INCLINADO COM BARRA", "supino_inclinado_barra", Muscle.CHEST),
//            Exercise("CRUCIFIXO RETO", "someUrl1",Muscles.CHEST),
//            Exercise("VOADOR PEITORAL", "someUrl1",Muscles.CHEST),
//            Exercise("FLEXÃO DE BRAÇOS", "someUrl1",Muscles.CHEST),
                Exercise("MERGULHO EM PARALELAS", "dip", Muscle.CHEST),
//            Exercise("SUPINO MÁQUINA", "someUrl1",Muscles.CHEST),
                // BACK
                Exercise("PUXADOR PULLEY", "puxador_pulley", Muscle.BACK),
                Exercise("REMADA CURVADA", "remada_curvada", Muscle.BACK),

                // SHOULDER
                Exercise("LEVANTAMENTO LATERAL", "levantamento_lateral", Muscle.SHOULDER),

                // TRICEPS
                Exercise("TRÍCEPS CORDA", "triceps_corda", Muscle.TRICEPS),
                Exercise("TRÍCEPS BARRA RETA", "triceps_pulley", Muscle.TRICEPS),
                Exercise("TRÍCEPS TESTA", "triceps_testa", Muscle.TRICEPS),
                // BICEPS
                Exercise("ROSCA MARTELO", "rosca_martelo", Muscle.BICEPS),
                Exercise("ROSCA DIRETA COM A BARRA", "rosca_direta_barra", Muscle.BICEPS),
                Exercise("ROSCA DIRETA NO PULLEY", "rosca_direta_pulley", Muscle.BICEPS),

                // QUADRICEPS
                Exercise("AGACHAMENTO", "squat", Muscle.QUADRICEPS),
                Exercise("LEVANTAMENTO TERRA", "deadlift", Muscle.QUADRICEPS),
//            Exercise("LEG PRESS", "someUrl3",Muscles.QUADRICEPS),


                // HARMSTRING
                Exercise("STIFF", "stiff", Muscle.HAMSTRING),
                Exercise("MESA FLEXORA", "mesa_flexora", Muscle.HAMSTRING),

                // GLUTES / CALVES
                Exercise("PANTURRILHA EM PÉ", "panturrilha_em_pe", Muscle.CALVES),
                Exercise("GÊMEOS SENTADO", "gemeos_sentado", Muscle.CALVES),


                // ABS
                Exercise("PRANCHA", "plank", Muscle.ABDOMINAL),
//            Exercise("ABDOMINAL INFRA", "someUrl3",Muscles.ABDOMINAL),

                // OTHERS
                Exercise("CORRIDA", "someUrl3", Muscle.OTHER),
                Exercise("BICICLETA", "someUrl3", Muscle.OTHER),
                Exercise("ESCADA", "someUrl3", Muscle.OTHER),
                Exercise("NATAÇÃO", "someUrl3", Muscle.OTHER)

            )
        }
    }
}