package com.apicela.training.models

data class Exercise(var exerciseName : String, var imageURL : String, var muscleType : Muscles)

{
    companion object {
        val listaExercises: MutableList<Exercise> by lazy { mutableListOf<Exercise>(
            // CHEST
            Exercise("SUPINO RETO COM HALTERES", "someUrl1",Muscles.CHEST),
            Exercise("SUPINO RETO COM BARRA", "someUrl1",Muscles.CHEST),
            Exercise("SUPINO INCLINADO COM HALTERES", "someUrl1",Muscles.CHEST),
            Exercise("SUPINO INCLINADO COM BARRA", "someUrl1",Muscles.CHEST),
            Exercise("CRUCIFIXO RETO", "someUrl1",Muscles.CHEST),
            Exercise("VOADOR PEITORAL", "someUrl1",Muscles.CHEST),
            Exercise("FLEXÃO DE BRAÇOS", "someUrl1",Muscles.CHEST),
            Exercise("MERGULHO EM PARALELAS", "someUrl1",Muscles.CHEST),
            Exercise("SUPINO MÁQUINA", "someUrl1",Muscles.CHEST),
            // BACK
            Exercise("PUXADOR PULLEY", "someUrl2",Muscles.BACK),
            Exercise("REMADA CURVADA", "someUrl3",Muscles.BACK),

            // SHOULDER
            Exercise("LEVANTAMENTO LATERAL", "someUrl3",Muscles.SHOULDER),

            // TRICEPS
            Exercise("TRÍCEPS CORDA", "someUrl3",Muscles.TRICEPS),
            Exercise("TRÍCEPS POLIA", "someUrl3",Muscles.TRICEPS),
            Exercise("TRÍCEPS TESTA", "someUrl3",Muscles.TRICEPS),
            // BICEPS
            Exercise("ROSCA MARTELO", "someUrl3",Muscles.BICEPS),
            Exercise("ROSCA DIRETA", "someUrl3",Muscles.BICEPS),
            Exercise("ROSCA DIRETA NO PULLEY", "someUrl3",Muscles.BICEPS),

            // QUADRICEPS
            Exercise("AGACHAMENTO", "someUrl3",Muscles.QUADRICEPS),
            Exercise("LEVANTAMENTO TERRA", "someUrl3",Muscles.QUADRICEPS),
            Exercise("LEG PRESS", "someUrl3",Muscles.QUADRICEPS),


            // HARMSTRING
            Exercise("STIFF", "someUrl3",Muscles.HAMSTRING),
            Exercise("MESA FLEXORA", "someUrl3",Muscles.HAMSTRING),

            // GLUTES / CALVES
            Exercise("PANTURRILHA EM PÉ UNILATERAL", "someUrl3",Muscles.CALVES),
            Exercise("GÊMEOS SENTADO", "someUrl3",Muscles.CALVES),


            // ABS
            Exercise("PRANCHA", "someUrl3",Muscles.ABDOMINAL),
            Exercise("ABDOMINAL INFRA", "someUrl3",Muscles.ABDOMINAL),

            // OTHERS
            Exercise("CORRIDA", "someUrl3",Muscles.OTHER),
            Exercise("BICICLETA", "someUrl3",Muscles.OTHER),
            Exercise("ESCADA", "someUrl3",Muscles.OTHER),
            Exercise("NATAÇÃO", "someUrl3",Muscles.OTHER)

            )
        }
    }
}