package com.apicela.training.data

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.room.Room
import com.apicela.training.HomeActivity
import java.io.FileOutputStream


object DataManager {
    private var database: Database? = null

    fun setHomeActivityDATABASE(context : Context){
        HomeActivity.DATABASE = getDatabase(context)
        Log.d("db", "setHomeActivity")

    }
    fun importDatabase(context: Context, inputUri: Uri): Boolean {
        return try {
            closeDatabase()
            val dbFile = context.getDatabasePath("apicela_training")

            context.contentResolver.openInputStream(inputUri)?.use { inputStream ->
                FileOutputStream(dbFile).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            setHomeActivityDATABASE(context)
            true // Importação bem-sucedida
        } catch (e: Exception) {
            e.printStackTrace()
            false // Importação falhou
        }
    }

    fun getDatabase(context: Context): Database {
        if (database == null) {
            synchronized(Database::class.java) {
                if (database == null) {
                    buildDB(context)
                }
            }
        }
        return database!!
    }

    fun closeDatabase() {
        database?.close()
        database = null
    }


    fun buildDB(context : Context){
        database =  Room.databaseBuilder(
            context.applicationContext,
            Database::class.java,
            "apicela_training"
        )
            .addMigrations(MIGRATION_3_4) // Adiciona a migração
            .build()
    }
}
