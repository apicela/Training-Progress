package com.apicela.training.models

enum class Muscle(val ptbr: String) {
    CHEST("Peitoral"), SHOULDER("Ombro"), BACK("Costas"), BICEPS("Bíceps"), TRICEPS("Tríceps"),
    OTHER("Outros"), ABDOMINAL("Abdominal"),
    QUADRICEPS("Quadríceps"), HAMSTRING("Posterior de Coxa"), CALVES("Panturrilha"), GLUTES("Glúteos");

    companion object {
        fun getAsList(): List<Muscle> {
            return values().toList()
        }

        fun getAsListPTBR(): List<String> {
            return values().map { it.ptbr }
        }
        fun getMuscleByPtbr(muscle: String): String {
            return Muscle.values().find { it.toString() == muscle }!!.ptbr
        }
    }
}