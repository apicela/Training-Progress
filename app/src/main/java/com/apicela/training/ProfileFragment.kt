package com.apicela.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.apicela.training.ui.utils.Components
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
//
class ProfileFragment  : Fragment(R.layout.fragment_profile) {
    lateinit var saveButton : Button
    lateinit var buttonDecrementDate : ImageButton
    lateinit var buttonIncrementDate : ImageButton
    lateinit var editTextObservation: EditText
    lateinit var editTextDate: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        linkViewFields(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val todayDate = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime().format(
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
        )
        setUpDateInfo(todayDate)
        setOnClick()
    }

    // @require date as dd/MM/yyyy
    fun setUpDateInfo(date : String){
        editTextDate.setText(date)
    }

    private fun setOnClick() {
        // saveButton
        saveButton.setOnClickListener {
            val observationText = editTextObservation.text.toString()
            TODO()

        }

        // edit text date
        editTextDate.setOnClickListener {
            Components.showDatePicker(editTextDate, requireContext())
            editTextDate.requestFocus() // Request focus after showing the DatePicker
        }
    }


    private fun linkViewFields(view: View) {
        // buttons
        saveButton = view.findViewById(R.id.buttonSave)
        buttonDecrementDate = view.findViewById(R.id.buttonDecrementDate)
        buttonIncrementDate = view.findViewById(R.id.buttonIncrementDate)

        // texts
        editTextObservation = view.findViewById(R.id.editTextObservation)
        editTextDate = view.findViewById(R.id.editTextDate)

    }
}
