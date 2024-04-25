package com.apicela.training.dialog
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.apicela.training.HomeActivity
import com.apicela.training.R
import com.apicela.training.models.Execution
import com.apicela.training.services.DivisionService
import com.apicela.training.services.ExecutionService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date


class RegisterExecutionDialog(private val exerciseId : String) : DialogFragment() {
    val executionService : ExecutionService = ExecutionService(HomeActivity.database)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.register_exercise, null)
        builder.setView(view)

        val editTextKG = view.findViewById<EditText>(R.id.editTextKG)
        val editTextRepeticoes = view.findViewById<EditText>(R.id.editTextRepeticoes)
        val buttonConfirmar = view.findViewById<Button>(R.id.buttonConfirmar)
        val buttonCancelar = view.findViewById<Button>(R.id.buttonCancelar)

        buttonConfirmar.setOnClickListener {
            // Aqui você pode obter os valores dos EditTexts e fazer o que quiser com eles
            val kg = editTextKG.text.toString().toFloatOrNull() ?: 0f
            val repetitions = editTextRepeticoes.text.toString().toIntOrNull() ?: 0
//            val dateString = "22/04/2024"
//            val format = SimpleDateFormat("dd/MM/yyyy")
//            var date  =  format.parse(dateString)

            // Faça o que precisar com os valores
            val execution = Execution(repetitions, kg,exerciseId,  Date())
            GlobalScope.launch {
                executionService.addExecutionToDatabase(execution)
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