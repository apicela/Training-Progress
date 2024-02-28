package com.apicela.training.models

enum class Muscles {
    CHEST, SHOULDER, BACK, BICEPS, TRICEPS,
    OTHER, ABDOMINAL,
    QUADRICEPS, HAMSTRING, CALVES, GLUTES;


    companion object {
        fun getAsList(): List<Muscles> {
            return Muscles.values().toList()
        }
    }
}