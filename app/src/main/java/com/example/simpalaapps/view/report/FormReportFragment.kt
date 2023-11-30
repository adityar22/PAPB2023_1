package com.example.simpalaapps.view.report

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.simpalaapps.R
import com.example.simpalaapps.model.AppDatabase
import com.example.simpalaapps.model.ReportEntity
import com.example.simpalaapps.presenter.form.FormReportContract
import com.example.simpalaapps.presenter.form.FormReportPresenter
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FormReportFragment : Fragment(), FormReportContract.View {

    private lateinit var presenter: FormReportContract.Presenter

    private lateinit var reportTypeEditText: EditText
    private lateinit var reporterNameEditText: EditText
    private lateinit var latitudeEditText: EditText
    private lateinit var longitudeEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var datePicker: DatePicker
    private lateinit var reporterEmailEditText: EditText
    private lateinit var photoButton: Button
    private lateinit var cancelButton: Button
    private lateinit var submitButton: Button

    private var currentPhotoUri: Uri? = null

    private var takePictureLauncher: ActivityResultLauncher<Uri>? = null

    companion object {
        const val CAMERA_REQUEST_CODE = 101
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_form_report, container, false)

        // Inisialisasi presenter
        presenter = FormReportPresenter(this, AppDatabase.getInstance(requireContext()).reportDao())

        reportTypeEditText = view.findViewById(R.id.editTextReportType)
        reporterNameEditText = view.findViewById(R.id.editTextReporterName)
        latitudeEditText = view.findViewById(R.id.editTextLatitude)
        longitudeEditText = view.findViewById(R.id.editTextLongitude)
        descriptionEditText = view.findViewById(R.id.editTextDescription)
        datePicker = view.findViewById(R.id.datePicker)
        reporterEmailEditText = view.findViewById(R.id.editTextReporterEmail)
        photoButton = view.findViewById(R.id.buttonChoosePhoto)
        cancelButton = view.findViewById(R.id.buttonCancel)
        submitButton = view.findViewById(R.id.buttonSubmit)

        takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success: Boolean ->
            if (success) {
                // The photo has been taken, and you can use currentPhotoUri to get the URI of the photo
                currentPhotoUri?.let {
                    // Use it as needed, for example, in your createReportFromInput function
                    val photoByteArray: ByteArray = retrievePhotoByteArray(it)
                }
            }
        }

        // Tambahkan logika UI dan onClickListener untuk menyimpan laporan
        val submitButton: Button = view.findViewById(R.id.buttonSubmit)
        submitButton.setOnClickListener {
            // Ambil data dari elemen-elemen UI (EditText, Spinner, dll.)
            val report = createReportFromInput()
            Log.i(report.reportType, report.reporterEmail)

            // Panggil metode presenter untuk menyimpan laporan
            lifecycleScope.launch {
                presenter.submitReport(report)
            }
        }

        photoButton.setOnClickListener {
            openCamera()
        }

        return view
    }

    private fun openCamera() {
        val photoFile: File? = try {
            createImageFile()
        } catch (ex: IOException) {
            // Handle the error
            null
        }

        photoFile?.also {
            val photoUri: Uri = FileProvider.getUriForFile(
                requireContext(),
                "com.example.simpalaapps",
                it
            )
            currentPhotoUri = photoUri
            takePictureLauncher?.launch(photoUri)
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        )
    }

    private fun retrievePhotoByteArray(photoUri: Uri): ByteArray {
        try {
            val inputStream = requireContext().contentResolver.openInputStream(photoUri)
            return inputStream?.readBytes() ?: byteArrayOf()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return byteArrayOf()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The photo has been taken, and you can use currentPhotoUri to get the URI of the photo
            currentPhotoUri?.let {
                // Use it as needed, for example, in your createReportFromInput function
                val photoByteArray: ByteArray = retrievePhotoByteArray(it)
            }
        }
    }

    private fun createReportFromInput(): ReportEntity {
        val reportType = reportTypeEditText.text.toString()
        val reporterName = reporterNameEditText.text.toString()
        val latitude = latitudeEditText.text.toString().toDouble()
        val longitude = longitudeEditText.text.toString().toDouble()
        val description = descriptionEditText.text.toString()
        val day = datePicker.dayOfMonth
        val month = datePicker.month + 1 // Month is zero-based
        val year = datePicker.year
        val reportingDate = "$year-$month-$day"
        val reporterEmail = reporterEmailEditText.text.toString()

        val photoByteArray: ByteArray = currentPhotoUri?.let { retrievePhotoByteArray(it) } ?: byteArrayOf()

        return ReportEntity(
            id = null,
            reportType = reportType,
            reporterName = reporterName,
            latitude = latitude,
            longitude = longitude,
            photo = photoByteArray,
            reportingDate = reportingDate,
            reporterEmail = reporterEmail,
            reportDesc = description
        )
    }
}

