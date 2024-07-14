package com.apicela.training

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.apicela.training.data.DataManager
import com.apicela.training.data.Database
import com.apicela.training.utils.Codes

class HomeActivity : AppCompatActivity() {
    companion object {
        lateinit var DATABASE: Database
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        DATABASE = DataManager.getDatabase(applicationContext)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Codes.REQUEST_CODE_CREATED && resultCode == Codes.RESULT_CODE_EXERCISE_CREATED) {
            recreate() // Isso reiniciará a Activity
        }
    }
}