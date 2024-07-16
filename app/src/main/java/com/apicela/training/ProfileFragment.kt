package com.apicela.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.apicela.training.ui.utils.Components
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
//
class ProfileFragment  : Fragment(R.layout.fragment_profile) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val saveButton: Button = view.findViewById(R.id.buttonSave)
        val editTextObservation: EditText = view.findViewById(R.id.editTextObservation)
        val editTextDate: EditText = view.findViewById(R.id.editTextDate)


        var date = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime().format(
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
        )
        editTextDate.setText(date as String)
        editTextDate.setOnClickListener {
            Components.showDatePicker(editTextDate, requireContext())
            editTextDate.requestFocus() // Request focus after showing the DatePicker
        }
        saveButton.setOnClickListener {
            val observationText = editTextObservation.text.toString()


        }

        return view
    }
}
