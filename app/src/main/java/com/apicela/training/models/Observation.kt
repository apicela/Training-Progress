package com.apicela.training.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey
import java.util.Date;

@Entity
data class  Observation(@PrimaryKey val date : Date,
                        val observation : String)
