package com.apicela.training.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

class DeleteItemDialog(private val item_name : String) : DialogFragment() {
    var confirmed: Boolean = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_delete_item, null)
        builder.setView(view)
        val messageView = view.findViewById<TextView>(R.id.message)
        val buttonConfirmar = view.findViewById<Button>(R.id.buttonConfirmar)
        val buttonCancelar = view.findViewById<Button>(R.id.buttonCancelar)

        val message = "${item_name}"
        messageView.text = message
        buttonConfirmar.setOnClickListener {
            confirmed = true
            dismiss() // Fecha o diálogo após a confirmação
        }

        buttonCancelar.setOnClickListener {
            confirmed = false;
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