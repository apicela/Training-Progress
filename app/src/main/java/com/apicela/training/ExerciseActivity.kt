package com.apicela.training

import androidx.appcompat.app.AppCompatActivity
import  com.apicela.training.models.Exercise
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import com.apicela.training.models.Muscles
import com.apicela.training.utils.UtilsComponents
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout

class ExerciseActivity : AppCompatActivity() {

    val exercises = Exercise.listaExercises
    private lateinit var containerLinearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        containerLinearLayout = findViewById(R.id.container)
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

        for (exercise in exercises) {
            val exerciseItem = UtilsComponents.createExerciseLine(this, exercise.exerciseName)
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

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterTextViews(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
    }

//    private fun filterTextViews(query: String) {
//        for (i in 0 until containerLinearLayout.childCount) {
//            val childView = containerLinearLayout.getChildAt(i)
//            if (childView is LinearLayout) {
//                // Acessar o LinearLayout aninhado
//                val nestedLayout = childView as LinearLayout
//
//                // Encontrar o TextView dentro do LinearLayout aninhado
//                val textView = nestedLayout.findViewById<TextView>(R.id.textView) // Assumindo que seu TextView tem um ID
//
//                if (textView != null) {
//                    // Fazer algo com o TextView, por exemplo, obter o texto
//                    val texto = textView.text.toString()
//                    // ...
//                }
//            }
//        }
//    }

//    fun findTextViewsByText(container: ViewGroup, textToSearch: String): List<TextView> {
//        val textViews = mutableListOf<TextView>()
//
//        fun searchViews(view: View) {
//            if (view is TextView) {
//                if (view.text.toString().contains(textToSearch)) {
//                    textViews.add(view)
//                }
//            } else if (view is ViewGroup) {
//                for (i in 0 until view.childCount) {
//                    searchViews(view.getChildAt(i))
//                }
//            }
//        }
//
//        searchViews(container)
//        return textViews
//    }

//private fun filterTextViews(query: String) {
//    val container: LinearLayout = findViewById(R.id.container)
//
//    // Choose the search approach based on your preference and API level:
//    val foundTextViews = when {
//        // Use recursive approach if compatible with your API level
//        Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
//            findTextViewsByTextRecursive(container, query)
//        }
//        // Use View.findAll if using AndroidX AppCompat library
//        else -> {
//            findTextViewsByTextFindAll(container, query)
//        }
//    }

    // Update UI based on found TextViews (e.g., highlight, hide others)
//    for (textView in foundTextViews) {
//        // Your UI modification logic here
//        textView.text = textView.text.toString().replace(query, "<mark>$query</mark>", true)
//    }
//}
    private fun filterTextViews(query: String) {
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
                                if (textView.text.contains(query, ignoreCase = true)) {
                                    Log.d("search", "visible: " + textView.text)
                                    innerLayout.visibility = LinearLayout.VISIBLE
                                } else {
                                    innerLayout.visibility = LinearLayout.GONE
                                }
                                // Exemplo de ação: exibir o texto do TextView
                            }
                        }
                    }
                }
            }
        }
    }
}