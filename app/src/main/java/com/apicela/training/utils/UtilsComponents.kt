package com.apicela.training.utils

import android.widget.Spinner

class UtilsComponents {
    companion object {
        fun getSpinnerSelectedItem(spinner: Spinner): String {
            val selectedIndex = spinner.selectedItemPosition
            val spinnerItem = spinner.getItemAtPosition(selectedIndex).toString()
            return spinnerItem
        }
    }
}