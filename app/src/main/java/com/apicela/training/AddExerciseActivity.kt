package com.apicela.training


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import com.apicela.training.data.Database
import com.apicela.training.models.Division
import com.apicela.training.models.Exercise
import com.apicela.training.models.Muscle
import com.apicela.training.ui.utils.ViewCreator
import com.apicela.training.utils.Codes
import com.apicela.training.utils.Codes.Companion.REQUEST_CODE_CREATED
import com.apicela.training.utils.Codes.Companion.RESULT_CODE_EXERCISE_CREATED
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.ShapeAppearanceModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AddExerciseActivity : AppCompatActivity() {

    private lateinit var containerLinearLayout: LinearLayout
    private lateinit var chestCardView: CardView
    private lateinit var backCardView: CardView
    private lateinit var shoulderCardView: CardView
    private lateinit var tricepsCardView: CardView
    private lateinit var bicepsCardView: CardView
    private lateinit var quadricepsCardView: CardView
    private lateinit var hamstringCardView: CardView
    private lateinit var glutesCalvesCardView: CardView
    private lateinit var absCardView: CardView
    private lateinit var othersCardView: CardView
    private lateinit var appearanceModel: ShapeAppearanceModel
    private lateinit var backButton: Button
    private lateinit var addExerciseToWorkoutButton: AppCompatButton
    private lateinit var exerciseList : List<Exercise>
    private lateinit var db : Database
    private lateinit var division : Division
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)
        val listOfCheckBox : MutableList<CheckBox> = mutableListOf()
        val listOfExercisesToAdd : MutableList<Exercise> = mutableListOf()
        var checkedItems : Int = 0;
        val division_id = intent.getStringExtra("division_id")
        // layouts
        containerLinearLayout = findViewById(R.id.container)
        backButton = findViewById(R.id.back_button)
        addExerciseToWorkoutButton = findViewById(R.id.add_exercise_to_workout)

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
        chestCardView = findViewById(R.id.cardView_chest)
        backCardView = findViewById(R.id.cardView_back)
        shoulderCardView = findViewById(R.id.cardView_shoulder)
        tricepsCardView = findViewById(R.id.cardView_triceps)
        bicepsCardView = findViewById(R.id.cardView_biceps)
        quadricepsCardView = findViewById(R.id.cardView_quadriceps)
        hamstringCardView = findViewById(R.id.cardView_hamstring)
        glutesCalvesCardView = findViewById(R.id.cardView_glutes_calves)
        absCardView = findViewById(R.id.cardView_abs)
        othersCardView = findViewById(R.id.cardView_others)

        CoroutineScope(Dispatchers.IO).launch {
            db = HomeActivity.database
                exerciseList = db.exerciseDao().getAllExercises()
            Log.d("teste", db.divisionDao()?.getAllDivisions().toString()   )
            division = db.divisionDao()?.getDivisionById(division_id!!)!!
            withContext(Dispatchers.Main) {
                for (exercise in exerciseList) {
                    val exerciseItem = ViewCreator.createExerciseLine(
                        applicationContext,
                        exercise,
                        appearanceModel,
                        true,
                        false
                    )

                    val checkBox = exerciseItem.findViewWithTag<CheckBox>("exercise_checkbox")
                    checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                        if (isChecked) {
                            checkedItems++;
                            listOfExercisesToAdd.add(exercise)
                            addExerciseToWorkoutButton.text =
                                "Adicionar exercícios (${checkedItems})"
                        } else {
                            checkedItems--;
                            listOfExercisesToAdd.remove(exercise)
                            addExerciseToWorkoutButton.text =
                                "Adicionar exercícios (${checkedItems})"
                        }
                    }

                    listOfCheckBox.add(checkBox)
                    when (exercise.muscleType) {
                        Muscle.BACK -> backLayout.addView(exerciseItem)
                        Muscle.CHEST -> chestLayout.addView(exerciseItem)
                        Muscle.SHOULDER -> shoulderLayout.addView(exerciseItem)
                        Muscle.TRICEPS -> tricepsLayout.addView(exerciseItem)
                        Muscle.BICEPS -> bicepsLayout.addView(exerciseItem)
                        Muscle.QUADRICEPS -> quadricepsLayout.addView(exerciseItem)
                        Muscle.HAMSTRING -> hamstringLayout.addView(exerciseItem)
                        Muscle.CALVES -> glutesCalvesLayout.addView(exerciseItem)
                        Muscle.GLUTES -> glutesCalvesLayout.addView(exerciseItem)
                        Muscle.ABDOMINAL -> absLayout.addView(exerciseItem)
                        else -> othersLayout.addView(exerciseItem)
                    }
                }
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

        backButton.setOnClickListener {
            finish()
        }

        addExerciseToWorkoutButton.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {

            if(division != null){
                val newListExercises =(division!!.listOfExercises + listOfExercisesToAdd).distinct()
                division?.listOfExercises = newListExercises
                db.divisionDao().update(division!!)
                Log.d("teste", division?.listOfExercises.toString())
            }
            }
            val resultIntent = Intent()
            setResult(Codes.RESULT_CODE_EXERCISE_CREATED, resultIntent)
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CREATED && resultCode == RESULT_CODE_EXERCISE_CREATED) {
            recreate() // Isso reiniciará a Activity
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
        if (query.isNullOrBlank()) {
            cardViewVisible.forEach { it to 1 }
        }

        for (i in 0 until containerLinearLayout.childCount) {
            val linearLayout = containerLinearLayout.getChildAt(i) as? LinearLayout
            // Verificando se o filho é um LinearLayout
            linearLayout?.let {
                // Iterando sobre os filhos do LinearLayout interno
                for (j in 0 until it.childCount) {
                    val innerLinearLayout = it.getChildAt(j) as? LinearLayout
//                        innerLinearLayout?.visibility = LinearLayout.GONE
                    // Verificando se o filho do LinearLayout interno é um LinearLayout
                    innerLinearLayout?.let { innerLayout ->
                        innerLinearLayout.visibility = LinearLayout.GONE
                        // Iterando sobre os filhos do LinearLayout interno
                        for (k in 0 until innerLayout.childCount) {
                            val view = innerLayout.getChildAt(k)
                            // Verificando se o filho é um TextView
                            if (view is TextView && view !is CheckBox) {
                                // Realize as ações desejadas com o TextView encontrado
                                if (view.text.contains(
                                        query,
                                        ignoreCase = true,
                                    ) || query.isNullOrBlank()
                                ) {
                                    innerLinearLayout.visibility = LinearLayout.VISIBLE
                                    when (view.tag) {
                                        Muscle.CHEST -> cardViewVisible[chestCardView] = 1
                                        Muscle.BACK -> cardViewVisible[backCardView] = 1
                                        Muscle.SHOULDER -> cardViewVisible[shoulderCardView] = 1
                                        Muscle.TRICEPS -> cardViewVisible[tricepsCardView] = 1
                                        Muscle.BICEPS -> cardViewVisible[bicepsCardView] = 1
                                        Muscle.QUADRICEPS -> cardViewVisible[quadricepsCardView] = 1
                                        Muscle.HAMSTRING -> cardViewVisible[hamstringCardView] = 1
                                        Muscle.ABDOMINAL -> cardViewVisible[absCardView] = 1
                                        Muscle.OTHER -> cardViewVisible[othersCardView] = 1
                                        else -> cardViewVisible[glutesCalvesCardView] = 1
                                    }
                                }
                            }
                        }
                    }
                }
                // apos ler tudo
                cardViewVisible.forEach { (muscle, value) ->
                    if (value == 1) {
                        muscle.visibility = CardView.VISIBLE
                    } else {
                        muscle.visibility = CardView.GONE
                    }
                }
            }
        }
    }
}