package com.apicela.training.data

import android.content.Context
import androidx.room.Room
import com.apicela.training.interfaces.Database


object DataManager {
    private var database: Database? = null

    fun getDatabase(context: Context): Database {
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