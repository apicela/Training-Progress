package com.apicela.training.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.UUID

@Entity
data class Exercise(@PrimaryKey var id: String , var exerciseName: String, var image: String, var muscleType: Muscle) :
    Serializable {
    @Ignore
    constructor( exerciseName: String,  image: String,  muscleType: Muscle) :
            this(UUID.randomUUID().toString(), exerciseName, image, muscleType)
    companion object {
        val listaExercises: MutableList<Exercise> by lazy {
            mutableListOf<Exercise>(
                // CHEST
                Exercise(UUID.randomUUID().toString(),"SUPINO RETO COM HALTERES", "supino_reto_halter", Muscle.CHEST),
                Exercise(UUID.randomUUID().toString(),"SUPINO RETO COM BARRA", "supino_reto_barra", Muscle.CHEST),
                Exercise(UUID.randomUUID().toString(),"SUPINO INCLINADO COM HALTERES", "supino_inclinado_halter", Muscle.CHEST),
                Exercise(UUID.randomUUID().toString(),"SUPINO INCLINADO COM BARRA", "supino_inclinado_barra", Muscle.CHEST),
//            Exercise("CRUCIFIXO RETO", "someUrl1",Muscles.CHEST),
//            Exercise("VOADOR PEITORAL", "someUrl1",Muscles.CHEST),
//            Exercise("FLEXÃO DE BRAÇOS", "someUrl1",Muscles.CHEST),
                Exercise(UUID.randomUUID().toString(),"MERGULHO EM PARALELAS", "dip", Muscle.CHEST),
//            Exercise("SUPINO MÁQUINA", "someUrl1",Muscles.CHEST),
                // BACK
                Exercise(UUID.randomUUID().toString(),"PUXADOR PULLEY", "puxador_pulley", Muscle.BACK),
                Exercise(UUID.randomUUID().toString(),"REMADA CURVADA", "remada_curvada", Muscle.BACK),

                // SHOULDER
                Exercise(UUID.randomUUID().toString(),"LEVANTAMENTO LATERAL", "levantamento_lateral", Muscle.SHOULDER),

                // TRICEPS
                Exercise(UUID.randomUUID().toString(),"TRÍCEPS CORDA", "triceps_corda", Muscle.TRICEPS),
                Exercise(UUID.randomUUID().toString(),"TRÍCEPS BARRA RETA", "triceps_pulley", Muscle.TRICEPS),
                Exercise(UUID.randomUUID().toString(),"TRÍCEPS TESTA", "triceps_testa", Muscle.TRICEPS),
                // BICEPS
                Exercise(UUID.randomUUID().toString(),"ROSCA MARTELO", "rosca_martelo", Muscle.BICEPS),
                Exercise(UUID.randomUUID().toString(),"ROSCA DIRETA COM A BARRA", "rosca_direta_barra", Muscle.BICEPS),
                Exercise(UUID.randomUUID().toString(),"ROSCA DIRETA NO PULLEY", "rosca_direta_pulley", Muscle.BICEPS),

                // QUADRICEPS
                Exercise(UUID.randomUUID().toString(),"AGACHAMENTO", "squat", Muscle.QUADRICEPS),
                Exercise(UUID.randomUUID().toString(),"LEVANTAMENTO TERRA", "deadlift", Muscle.QUADRICEPS),
//            Exercise("LEG PRESS", "someUrl3",Muscles.QUADRICEPS),


                // HARMSTRING
                Exercise(UUID.randomUUID().toString(),"STIFF", "stiff", Muscle.HAMSTRING),
                Exercise(UUID.randomUUID().toString(),"MESA FLEXORA", "mesa_flexora", Muscle.HAMSTRING),

                // GLUTES / CALVES
                Exercise(UUID.randomUUID().toString(),"PANTURRILHA EM PÉ", "panturrilha_em_pe", Muscle.CALVES),
                Exercise(UUID.randomUUID().toString(),"GÊMEOS SENTADO", "gemeos_sentado", Muscle.CALVES),


                // ABS
                Exercise(UUID.randomUUID().toString(),"PRANCHA", "plank", Muscle.ABDOMINAL),
//            Exercise("ABDOMINAL INFRA", "someUrl3",Muscles.ABDOMINAL),

                // OTHERS
                Exercise(UUID.randomUUID().toString(),"CORRIDA", "someUrl3", Muscle.OTHER),
                Exercise(UUID.randomUUID().toString(),"BICICLETA", "someUrl3", Muscle.OTHER),
                Exercise(UUID.randomUUID().toString(),"ESCADA", "someUrl3", Muscle.OTHER),
                Exercise(UUID.randomUUID().toString(),"NATAÇÃO", "someUrl3", Muscle.OTHER)

            )
        }
    }
}