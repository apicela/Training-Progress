package com.apicela.training.data

import android.content.Context
import androidx.room.Room


object DataManager {
    private var database: Database? = null

    fun getDatabase(context: Context): Database {
        if (database == null) {
            synchronized(Database::class.java) {
                if (database == null) {
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        Database::class.java,
                        "db_app_training"
                    )
                        // Habilitar migração automática
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
        }
        return database!!
    }
}
