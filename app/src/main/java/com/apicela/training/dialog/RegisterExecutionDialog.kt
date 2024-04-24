package com.apicela.training.dialog
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.apicela.training.R


class RegisterExecutionDialog : DialogFragment() {
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
            val repeticoes = editTextRepeticoes.text.toString().toIntOrNull() ?: 0
            // Faça o que precisar com os valores

            dismiss() // Fecha o diálogo após a confirmação
        }

        buttonCancelar.setOnClickListener {
            dismiss() // Fecha o diálogo sem fazer nada
        }

        return builder.create()
    }
}