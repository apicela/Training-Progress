package com.apicela.training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.apicela.training.utils.UtilsComponents

class DivisionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_division)
            val container = findViewById<LinearLayout>(R.id.container)
        val x = UtilsComponents.createDivisionLine(this, "A","division_a", null )
        container.addView(x)
        val y = UtilsComponents.createDivisionLine(this, "B","division_b", null )
        container.addView(y)

    }
}