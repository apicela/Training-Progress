package com.apicela.training.dialog
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.apicela.training.HomeActivity
import com.apicela.training.R
import com.apicela.training.models.Execution
import com.apicela.training.services.ExecutionService
import com.apicela.training.ui.utils.Components
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date


class RegisterExecutionDialog(private val exerciseId : String, private val executionId : String?, private val context : Context) : DialogFragment() {
    val executionService : ExecutionService = ExecutionService(HomeActivity.database)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.register_exercise, null)
        builder.setView(view)
        val editTextKG = view.findViewById<EditText>(R.id.editTextKG)
        val editTextDate = view.findViewById<EditText>(R.id.editTextDate)
        val editTextRepeticoes = view.findViewById<EditText>(R.id.editTextRepeticoes)
        val buttonConfirmar = view.findViewById<Button>(R.id.buttonConfirmar)
        val buttonCancelar = view.findViewById<Button>(R.id.buttonCancelar)

        if(executionId == null){
            val lastExercise = runBlocking { executionService.getLastInsertedExecution(exerciseId) }
            if(lastExercise !== null){
                editTextKG.setText("${lastExercise.kg}")
                editTextRepeticoes.setText("${lastExercise.repetitions}")
            }
        } else{
            val executionToUpdate = runBlocking { executionService.getExecutionById(executionId) }
            if(executionToUpdate !== null){
                editTextKG.setText("${executionToUpdate.kg}")
                editTextRepeticoes.setText("${executionToUpdate.repetitions}")
            }
        }



        var date = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime().format(
            DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        editTextDate.setText(date as String)
        editTextDate.setOnClickListener{
            Components.showDatePicker(editTextDate, context)
            editTextDate.requestFocus() // Request focus after showing the DatePicker
        }
        buttonConfirmar.setOnClickListener {
            // Aqui você pode obter os valores dos EditTexts e fazer o que quiser com eles
            val kg = editTextKG.text.toString().toFloatOrNull() ?: 0f
            val repetitions = editTextRepeticoes.text.toString().toIntOrNull() ?: 0
            val format = SimpleDateFormat("dd/MM/yyyy")
            val formattedDate = format.parse(editTextDate.text.toString()) as Date
            if(executionId == null){
            val execution = Execution(repetitions, kg,exerciseId, formattedDate)
                GlobalScope.launch {
                    executionService.addExecutionToDatabase(execution)
                }
            } else{
                val execution = Execution(executionId, repetitions, kg,exerciseId, formattedDate)
                GlobalScope.launch {
                    executionService.updateExecutionObject(execution)
                }
            }
            dismiss() // Fecha o diálogo após a confirmação
        }

        buttonCancelar.setOnClickListener {
            dismiss() // Fecha o diálogo sem fazer nada
        }

        return builder.create()
    }

    var onDismissListener: (() -> Unit)? = null

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener?.invoke()
    }
}