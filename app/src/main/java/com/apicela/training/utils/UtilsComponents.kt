package com.apicela.training.utils

import android.view.View
import android.widget.CheckBox
import android.widget.Spinner

class UtilsComponents {
    companion object {
        fun getSpinnerSelectedItem(spinner: Spinner): String {
            val selectedIndex = spinner.selectedItemPosition
            val spinnerItem = spinner.getItemAtPosition(selectedIndex).toString()
            return spinnerItem
        }

        fun <T> turnListOfViewVisible(listOfView: List<T>) {
            if (listOfView.all { it is CheckBox }) {
                listOfView.forEach { (it as CheckBox).visibility = View.VISIBLE }
            }
        }

    }
}