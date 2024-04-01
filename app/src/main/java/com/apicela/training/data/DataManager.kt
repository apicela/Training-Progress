package com.apicela.training.data

import android.content.Context
import androidx.room.Room


object DataManager {
    private var database: Database? = null


    fun getDatabase(context: Context): Database {
//        context.deleteDatabase("my_database")
//        database = null // Reinicializar a instância do banco de dados

        if (database == null) {
            synchronized(Database::class.java) {
                if (database == null) {
                    database = Room.databaseBuilder(
                        context.applicationContext,
                        Database::class.java,
                        "my_database"
                    ).build()
                }
            }
        }
        return database!!
    }
}
