package com.apicela.training.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.UUID

@Entity
data class Exercise(
    @PrimaryKey var id: String,
    var exerciseName: String,
    var image: String,
    var muscleType: Muscle
) :
    Serializable {
    @Ignore
    constructor(exerciseName: String, image: String, muscleType: Muscle) :
            this(UUID.randomUUID().toString(), exerciseName, image, muscleType)

    companion object {
        val listaExercises: MutableList<Exercise> by lazy {
            mutableListOf<Exercise>(
                // CHEST
                Exercise(
                    UUID.randomUUID().toString(),
                    "SUPINO RETO COM HALTERES",
                    "supino_reto_halter",
                    Muscle.CHEST
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "SUPINO RETO COM BARRA",
                    "supino_reto_barra",
                    Muscle.CHEST
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "SUPINO INCLINADO COM HALTERES",
                    "supino_inclinado_halter",
                    Muscle.CHEST
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "SUPINO INCLINADO COM BARRA",
                    "supino_inclinado_barra",
                    Muscle.CHEST
                ),
                Exercise(
                    "CRUCIFIXO RETO",
                    "https://www.mundoboaforma.com.br/wp-content/uploads/2019/11/03081301-crucifixo-com-halteres.gif",
                    Muscle.CHEST
                ),
                Exercise(
                    "VOADOR PEITORAL",
                    "https://www.blog.treinoemalta.com.br/wp-content/uploads/2023/07/Peck-Deck.gif",
                    Muscle.CHEST
                ),
                Exercise(
                    "FLEXÃO DE BRAÇOS",
                    "https://www.hipertrofia.org/blog/wp-content/uploads/2019/12/negative-push-up.gif",
                    Muscle.CHEST
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "MERGULHO EM PARALELAS",
                    "dip",
                    Muscle.CHEST
                ),
                Exercise(
                    "SUPINO MÁQUINA",
                    "https://s6.gifyu.com/images/supino-reto_na-maquina.gif",
                    Muscle.CHEST
                ),

                // BACK
                Exercise(
                    UUID.randomUUID().toString(),
                    "PUXADOR PULLEY",
                    "puxador_pulley",
                    Muscle.BACK
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "BARRA FIXA",
                    "https://www.mundoboaforma.com.br/wp-content/uploads/2020/12/costas-barra-fixa-pegada-aberta-palma-para-frente-chinup.gif",
                    Muscle.BACK
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "LEVANTAMENTO TERRA",
                    "deadlift",
                    Muscle.BACK
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "REMADA CURVADA",
                    "remada_curvada",
                    Muscle.BACK
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "REMADA BAIXA",
                    "https://www.mundoboaforma.com.br/wp-content/uploads/2021/09/remada-sentado-com-cabos-e-triangulo-para-costas.gif",
                    Muscle.BACK
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "REMADA CAVALINHO",
                    "https://www.mundoboaforma.com.br/wp-content/uploads/2020/12/costas-remada-em-pe-com-barra-T.gif",
                    Muscle.BACK
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "REMADA NO BANCO",
                    "https://www.mundoboaforma.com.br/wp-content/uploads/2020/12/costas-remada-no-banco-inclinado-com-halteres.gif",
                    Muscle.BACK
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "REMADA UNILATERAL",
                    "https://static.wixstatic.com/media/2edbed_cf8feb6f79494833b887104bc358843d~mv2.gif",
                    Muscle.BACK
                ),

                // SHOULDER
                Exercise(
                    UUID.randomUUID().toString(),
                    "LEVANTAMENTO FRONTAL",
                    "https://www.hipertrofia.org/blog/wp-content/uploads/2023/11/dumbbell-front-raise.gif",
                    Muscle.SHOULDER
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "LEVANTAMENTO LATERAL",
                    "levantamento_lateral",
                    Muscle.SHOULDER
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "DESENVOLVIMENTO MÁQUINA",
                    "https://karoldeliberato.com.br/wp-content/uploads/2023/04/image70.gif",
                    Muscle.SHOULDER
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "DESENVOLVIMENTO HALTERES",
                    "https://karoldeliberato.com.br/wp-content/uploads/2023/04/image36-1.gif",
                    Muscle.SHOULDER
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "CRUCIFIXO INVERTIDO",
                    "https://www.mundoboaforma.com.br/wp-content/uploads/2020/12/ombros-crucifixo-invertido-com-halteres.gif",
                    Muscle.SHOULDER
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "VOADOR INVERSO",
                    "https://www.mundoboaforma.com.br/wp-content/uploads/2020/12/ombros-voador-invertido-na-maquina.gif",
                    Muscle.SHOULDER
                ),

                // TRICEPS
                Exercise(
                    UUID.randomUUID().toString(),
                    "TRICEPS CORDA",
                    "triceps_corda",
                    Muscle.TRICEPS
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "TRICEPS BARRA RETA",
                    "triceps_pulley",
                    Muscle.TRICEPS
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "TRICEPS FRANCES",
                    "https://www.hipertrofia.org/blog/wp-content/uploads/2023/10/dumbbell-seated-triceps-extension.gif",
                    Muscle.TRICEPS
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "TRICEPS TESTA",
                    "triceps_testa",
                    Muscle.TRICEPS
                ),

                // BICEPS
                Exercise(
                    UUID.randomUUID().toString(),
                    "ROSCA MARTELO",
                    "rosca_martelo",
                    Muscle.BICEPS
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "ROSCA DIRETA COM A BARRA",
                    "rosca_direta_barra",
                    Muscle.BICEPS
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "ROSCA DIRETA NO PULLEY",
                    "rosca_direta_pulley",
                    Muscle.BICEPS
                ),

                // QUADRICEPS
                Exercise(UUID.randomUUID().toString(), "AGACHAMENTO", "squat", Muscle.QUADRICEPS),
                Exercise(
                    UUID.randomUUID().toString(),
                    "LEVANTAMENTO TERRA",
                    "deadlift",
                    Muscle.QUADRICEPS
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "CADEIRA EXTENSORA",
                    "https://media.tenor.com/fNeMiJuGmEcAAAAM/cadeira-extensora-extensora.gif",
                    Muscle.QUADRICEPS
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "AGACHAMENTO SUMÔ",
                    "https://www.mundoboaforma.com.br/wp-content/uploads/2021/09/agachamento-sumo-sem-halter.gif",
                    Muscle.QUADRICEPS
                ),
                Exercise(
                    "LEG PRESS",
                    "https://www.inspireusafoundation.org/wp-content/uploads/2022/10/leg-press.gif",
                    Muscle.QUADRICEPS
                ),


                // HARMSTRING
                Exercise(UUID.randomUUID().toString(), "STIFF", "stiff", Muscle.HAMSTRING),
                Exercise(
                    UUID.randomUUID().toString(),
                    "MESA FLEXORA",
                    "mesa_flexora",
                    Muscle.HAMSTRING
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "BOM DIA",
                    "https://www.hipertrofia.org/blog/wp-content/uploads/2023/09/barbell-good-morning.gif",
                    Muscle.HAMSTRING
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "ELEVAÇÃO PÉLVICA",
                    "https://static.wixstatic.com/media/2edbed_852ea3938607497aa100eb79e600e11a~mv2.gif",
                    Muscle.HAMSTRING
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "EXTENSÃO DE QUADRIL",
                    "https://www.mundoboaforma.com.br/wp-content/uploads/2020/11/coice-no-cabo.gif",
                    Muscle.HAMSTRING
                ),

                // GLUTES / CALVES
                Exercise(
                    UUID.randomUUID().toString(),
                    "PANTURRILHA EM PÉ",
                    "panturrilha_em_pe",
                    Muscle.CALVES
                ),
                Exercise(
                    UUID.randomUUID().toString(),
                    "GÊMEOS SENTADO",
                    "gemeos_sentado",
                    Muscle.CALVES
                ),


                // ABS
                Exercise(UUID.randomUUID().toString(), "PRANCHA", "plank", Muscle.ABDOMINAL),
                Exercise(
                    "ABDOMINAL INFRA",
                    "https://www.mundoboaforma.com.br/wp-content/uploads/2021/04/abdominal-no-chao-com-elevacao-de-pernas-semi-dobradas.gif",
                    Muscle.ABDOMINAL
                ),

                // OTHERS
                Exercise(UUID.randomUUID().toString(), "CORRIDA", "someUrl3", Muscle.OTHER),
                Exercise(UUID.randomUUID().toString(), "BICICLETA", "someUrl3", Muscle.OTHER),
                Exercise(UUID.randomUUID().toString(), "ESCADA", "someUrl3", Muscle.OTHER),
                Exercise(UUID.randomUUID().toString(), "NATAÇÃO", "someUrl3", Muscle.OTHER)

            )
        }
    }
}