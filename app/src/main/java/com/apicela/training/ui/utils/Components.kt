package com.apicela.training.ui.utils

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.EditText
import com.apicela.training.R
import java.time.LocalDate
import java.util.Calendar

class Components {
    companion object{

        fun showDatePicker(dateTimeText: EditText, context: Context) {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Create a DatePickerDialog with a custom onDateSet listener
            val datePickerDialog = DatePickerDialog(
                context,
                R.style.DatePickerDialogStyle, // Estilo personalizado
                { view, selectedYear, selectedMonth, selectedDay ->
                    // Logic to handle the selected date
                    val formattedMonth = if (selectedMonth + 1 < 10) {
                        "0${selectedMonth + 1}" // Adiciona um zero se o mês for menor que 10
                    } else {
                        "${selectedMonth + 1}"
                    }
                    val selectedDateTime = "$selectedDay/$formattedMonth/$selectedYear"
                    Log.d("date", " selected  :  ${selectedDateTime}")
                    dateTimeText.setText(selectedDateTime)
                },
                year,
                month,
                day
            )

            // Show the DatePickerDialog
            datePickerDialog.show()
        }




        fun formatDateAsString(date : LocalDate) : String{
            val day = date.dayOfMonth
            val month = date.month
            val year = date.year
            return  "${day}/${month}/${year}"
        }

    }
}