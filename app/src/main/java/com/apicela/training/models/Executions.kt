package com.apicela.training.models

import java.util.Date

data class Executions(var sets : Integer, var repetitions : Integer, val exercise : Exercises, var date : Date)
