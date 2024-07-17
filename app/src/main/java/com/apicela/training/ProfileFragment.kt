package com.apicela.training

import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.result.ActivityResult
import androidx.fragment.app.Fragment
import com.apicela.training.models.Observation
import com.apicela.training.services.ObservationService
import com.apicela.training.ui.utils.Components
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.jvm.Throws
import android.os.Environment
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.apicela.training.data.DataManager
import java.nio.channels.FileChannel

//
class ProfileFragment  : Fragment(R.layout.fragment_profile) {
    lateinit var saveButton : Button
    lateinit var exportData : Button
    lateinit var importData : Button
    lateinit var buttonDecrementDate : ImageButton
    lateinit var buttonIncrementDate : ImageButton
    lateinit var editTextObservation: EditText
    lateinit var editTextDate: EditText
    lateinit var observationService: ObservationService

    private lateinit var backupLauncher: ActivityResultLauncher<Intent>
    private lateinit var importLauncher: ActivityResultLauncher<Intent>

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
        setUpVariables()
        val todayDate = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime().format(
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
        )
        setUpDateInfo(todayDate)
        setOnClick()


        backupLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    backupDatabase(requireContext(), uri)
                }
            }
        }

        importLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    val success = DataManager.importDatabase(requireContext(), uri)
                    if (success) {
                        Toast.makeText(context, "Database imported successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Failed to import database", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


    }

    private fun setUpVariables() {
        observationService = ObservationService()
    }

    // @require date as dd/MM/yyyy
    fun setUpDateInfo(date : String){
        editTextDate.setText(date)
        val observationFromDB = runBlocking { observationService.getObservationByDate(date) }
        Log.d("ProfileFragment", "observationFromDB: ${observationFromDB.toString()}")
        val observationText = observationFromDB?.observation ?: ""
        editTextObservation.setText(observationText)
    }

    private fun setOnClick() {
        // saveButton
        saveButton.setOnClickListener {
            val observationText = editTextObservation.text.toString()
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val dateString = editTextDate.text.toString()

            val observation = Observation(dateFormat.parse(dateString)!!, observationText)
//            Log.d("ProfileFragment", "date from editText: ${Date(editTextDate.text.toString())} + editText.text : ${editTextDate.text.toString()}")
            CoroutineScope(Dispatchers.IO).launch {
               observationService.addObservation(observation)
            }
        }

        buttonIncrementDate.setOnClickListener{
            val newDate = updateDate(editTextDate.text.toString(), 1)
            editTextDate.setText(newDate)
            setUpDateInfo(newDate)
        }
        buttonDecrementDate.setOnClickListener{
            val newDate = updateDate(editTextDate.text.toString(), -1)
            editTextDate.setText(newDate)
            setUpDateInfo(newDate)
        }

        exportData.setOnClickListener{
            startBackupProcess()
        }

        importData.setOnClickListener{
            startImportProcess()
        }

        // edit text date
        editTextDate.setOnClickListener {
            Components.showDatePicker(editTextDate, requireContext())
            editTextDate.requestFocus() // Request focus after showing the DatePicker
        }
    }


    private fun startImportProcess() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/octet-stream"
        }
        importLauncher.launch(intent)
    }

    // Função para copiar o banco de dados
    fun backupDatabase(context: Context, outputUri: Uri) {
        DataManager.closeDatabase() // Fechar o banco de dados para garantir que todas as operações pendentes sejam concluídas
        Log.d("db", "backupDB")
        val dbFile = context.getDatabasePath("apicela_training")

        context.contentResolver.openOutputStream(outputUri)?.use { outputStream ->
            FileInputStream(dbFile).use { inputStream ->
                inputStream.copyTo(outputStream)
            }
        }
        DataManager.setHomeActivityDATABASE(requireContext())
    }


    private fun startBackupProcess() {
        Log.d("db", "start backup process")
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/octet-stream"
            putExtra(Intent.EXTRA_TITLE, "apicela_training_backup.db")
        }
        backupLauncher.launch(intent)
    }



    private fun linkViewFields(view: View) {
        // buttons
        saveButton = view.findViewById(R.id.buttonSave)
        buttonDecrementDate = view.findViewById(R.id.buttonDecrementDate)
        buttonIncrementDate = view.findViewById(R.id.buttonIncrementDate)
        exportData = view.findViewById(R.id.exportData)
        importData = view.findViewById(R.id.importData)

        // texts
        editTextObservation = view.findViewById(R.id.editTextObservation)
        editTextDate = view.findViewById(R.id.editTextDate)

    }

    fun updateDate(dateAsString: String, days : Int) : String{
        val currentDate = LocalDate.parse(dateAsString, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        val updatedDate = currentDate.plusDays(days.toLong()).format(
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
        )
        return updatedDate;
    }

}
