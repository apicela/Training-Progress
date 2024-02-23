package com.apicela.training

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.marginStart
import com.apicela.training.models.Exercise
import com.apicela.training.models.Muscles
import com.apicela.training.utils.UtilsComponents
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.ShapeAppearanceModel
import de.hdodenhof.circleimageview.CircleImageView

class ExerciseActivity : AppCompatActivity() {

    val exercises = Exercise.listaExercises
    private lateinit var containerLinearLayout: LinearLayout
    private lateinit var chestCardView : CardView
    private lateinit var backCardView : CardView
    private lateinit var shoulderCardView : CardView
    private lateinit var tricepsCardView : CardView
    private lateinit var bicepsCardView : CardView
    private lateinit var quadricepsCardView : CardView
    private lateinit var hamstringCardView : CardView
    private lateinit var glutesCalvesCardView : CardView
    private lateinit var absCardView : CardView
    private lateinit var othersCardView : CardView
    private lateinit var appearanceModel : ShapeAppearanceModel
    private lateinit var plusButton : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        // layouts
        containerLinearLayout = findViewById(R.id.container)
        plusButton = findViewById(R.id.plus_button)
        val testLayout = findViewById<LinearLayout>(R.id.testLayout)

        val backLayout = findViewById<LinearLayout>(R.id.backLayout)
        val chestLayout = findViewById<LinearLayout>(R.id.chestLayout)
        val shoulderLayout = findViewById<LinearLayout>(R.id.shoulderLayout)
        val othersLayout = findViewById<LinearLayout>(R.id.others)
        val tricepsLayout = findViewById<LinearLayout>(R.id.tricepsLayout)
        val bicepsLayout = findViewById<LinearLayout>(R.id.bicepsLayout)
        val quadricepsLayout = findViewById<LinearLayout>(R.id.quadricepsLayout)
        val hamstringLayout = findViewById<LinearLayout>(R.id.hamstringLayout)
        val glutesCalvesLayout = findViewById<LinearLayout>(R.id.glutes_calvesLayout)
        val absLayout = findViewById<LinearLayout>(R.id.absLayout)


        for (i in 0 until testLayout.childCount) {
            val child = testLayout.getChildAt(i)
            if (child is ShapeableImageView) {
                appearanceModel = child.shapeAppearanceModel
            }
        }


        // card views

        chestCardView  = findViewById(R.id.cardView_chest)
         backCardView = findViewById(R.id.cardView_back)
         shoulderCardView = findViewById(R.id.cardView_shoulder)
         tricepsCardView  = findViewById(R.id.cardView_triceps)
         bicepsCardView  = findViewById(R.id.cardView_biceps)
          quadricepsCardView  = findViewById(R.id.cardView_quadriceps)
         hamstringCardView = findViewById(R.id.cardView_hamstring)
         glutesCalvesCardView  = findViewById(R.id.cardView_glutes_calves)
          absCardView = findViewById(R.id.cardView_abs)
         othersCardView = findViewById(R.id.cardView_others)


        for (exercise in exercises) {
            val exerciseItem = UtilsComponents.createExerciseLine(this, exercise.exerciseName, exercise.muscleType, appearanceModel, exercise.image)
            when (exercise.muscleType) {
                Muscles.BACK -> backLayout.addView(exerciseItem)
                Muscles.CHEST -> chestLayout.addView(exerciseItem)
                Muscles.SHOULDER -> shoulderLayout.addView(exerciseItem)
                Muscles.TRICEPS -> tricepsLayout.addView(exerciseItem)
                Muscles.BICEPS -> bicepsLayout.addView(exerciseItem)
                Muscles.QUADRICEPS -> quadricepsLayout.addView(exerciseItem)
                Muscles.HAMSTRING -> hamstringLayout.addView(exerciseItem)
                Muscles.CALVES -> glutesCalvesLayout.addView(exerciseItem)
                Muscles.GLUTES -> glutesCalvesLayout.addView(exerciseItem)
                Muscles.ABDOMINAL -> absLayout.addView(exerciseItem)
                else -> othersLayout.addView(exerciseItem)

            }
        }

        val searchView = findViewById<SearchView>(R.id.searchView)
        val searchEditText = searchView.findViewById<View>(
            searchView.context.resources.getIdentifier(
                "android:id/search_src_text",
                null,
                null
            )
        ) as EditText
        searchEditText.setHintTextColor(Color.parseColor("#c5c2b5")) // Replace with desired color
        searchEditText.setTextColor(Color.parseColor("#c5c2b5"))


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterTextViews(newText)
                return true
            }


        }
        )

        plusButton.setOnClickListener{
            Log.d("button", "clicked")
        }
    }


    private fun filterTextViews(query: String) {

        val cardViewVisible = mutableMapOf(
            chestCardView to 0,
            backCardView to 0,
            shoulderCardView to 0,
            tricepsCardView to 0,
            bicepsCardView to 0,
            quadricepsCardView to 0,
            hamstringCardView to 0,
            glutesCalvesCardView to 0,
            absCardView to 0,
            othersCardView to 0
        )
        if(query.isNullOrBlank()){
            cardViewVisible.forEach { it to 1  }
        }

        for (i in 0 until containerLinearLayout.childCount) {
            val linearLayout = containerLinearLayout.getChildAt(i) as? LinearLayout
            // Verificando se o filho é um LinearLayout
            linearLayout?.let {
                // Iterando sobre os filhos do LinearLayout interno
                for (j in 0 until it.childCount) {
                    val innerLinearLayout = it.getChildAt(j) as? LinearLayout
                    // Verificando se o filho do LinearLayout interno é um LinearLayout
                    innerLinearLayout?.let { innerLayout ->
                        // Iterando sobre os filhos do LinearLayout interno
                        for (k in 0 until innerLayout.childCount) {
                            val view = innerLayout.getChildAt(k)
                            // Verificando se o filho é um TextView
                            if (view is TextView) {
                                // Realize as ações desejadas com o TextView encontrado
                                val textView = view as TextView
                                if (textView.text.contains(query, ignoreCase = true) || query.isNullOrBlank()) {
                                    innerLayout.visibility = LinearLayout.VISIBLE
                                    when(textView.tag){
                                        Muscles.CHEST ->  cardViewVisible[chestCardView] = 1
                                        Muscles.BACK -> cardViewVisible[backCardView] = 1
                                        Muscles.SHOULDER -> cardViewVisible[shoulderCardView] = 1
                                        Muscles.TRICEPS ->cardViewVisible[tricepsCardView] = 1
                                        Muscles.BICEPS -> cardViewVisible[bicepsCardView] = 1
                                        Muscles.QUADRICEPS -> cardViewVisible[quadricepsCardView] = 1
                                        Muscles.HAMSTRING -> cardViewVisible[hamstringCardView] = 1
                                        Muscles.ABDOMINAL -> cardViewVisible[absCardView] = 1
                                        Muscles.OTHER -> cardViewVisible[othersCardView] = 1
                                        else -> cardViewVisible[glutesCalvesCardView] = 1
                                    }
                                } else {
                                    innerLayout.visibility = LinearLayout.GONE
                                }
                            }
                        }
                    }
                }
                // apos ler tudo
                cardViewVisible.forEach{ (muscle, value ) ->
                    if(value == 1) {
                        muscle.visibility = CardView.VISIBLE
                    } else {
                        muscle.visibility = CardView.GONE
                    }
                }
            }
        }
    }
}