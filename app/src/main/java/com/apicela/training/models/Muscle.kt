package com.apicela.training.models

enum class Muscle {
    CHEST, SHOULDER, BACK, BICEPS, TRICEPS,
    OTHER, ABDOMINAL,
    QUADRICEPS, HAMSTRING, CALVES, GLUTES;


    companion object {
        fun getAsList(): List<Muscle> {
            return Muscle.values().toList()
        }
    }
}