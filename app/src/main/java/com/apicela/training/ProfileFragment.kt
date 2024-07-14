package com.apicela.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment  : Fragment(R.layout.fragment_profile) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val saveButton: Button = view.findViewById(R.id.buttonSave)
        val editTextObservation: EditText = view.findViewById(R.id.editTextObservation)
        val textViewDate: TextView = view.findViewById(R.id.textViewDate)

        saveButton.setOnClickListener {
            val observationText = editTextObservation.text.toString()


        }

        return view
    }
}
