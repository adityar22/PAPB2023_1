package com.example.papb1.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.papb1.R
import com.example.papb1.databinding.FragmentDashboardBinding
import android.app.Activity
import android.content.Intent
import java.text.SimpleDateFormat
import java.util.Date

class DashboardFragment : AppCompatActivity() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var locationEditText: EditText
    private lateinit var facultyEditText: EditText
    private lateinit var dateEditText: EditText
    private lateinit var photoImageView: ImageView
    private lateinit var wasteTypeEditText: EditText

    // SharedPreferences for storing form data
    private lateinit var sharedPreferences: SharedPreferences

    // Dummy data (replace with your actual data source)
    private var formData: FormData = FormData("", "", "", null, "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_dashboard)

        // Initialize UI components
        locationEditText = findViewById(R.id.etLocation)
        facultyEditText = findViewById(R.id.etFaculty)
        dateEditText = findViewById(R.id.etDate)
        photoImageView = findViewById(R.id.ivPhotoPreview)
        wasteTypeEditText = findViewById(R.id.etWasteType)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("FormData", Context.MODE_PRIVATE)

        // Handle photo capture (you need to implement this)
        val capturePhotoButton: Button = findViewById(R.id.btnAddPhoto)
        capturePhotoButton.setOnClickListener {
            // Implement photo capture logic here
        }

        // Implement submission logic (e.g., save report data to a database)
        val submitButton: Button = findViewById(R.id.btnSubmit)
        submitButton.setOnClickListener {
            // Implement report submission logic here

            // Save form data to SharedPreferences
            saveFormDataToSharedPreferences()
        }
    }

    override fun onResume() {
        super.onResume()
        // Reload form data from SharedPreferences
        loadFormDataFromSharedPreferences()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Save form data to SharedPreferences when the activity is destroyed
        saveFormDataToSharedPreferences()
    }

    private fun saveFormDataToSharedPreferences() {
        val editor = sharedPreferences.edit()
        editor.putString("location", locationEditText.text.toString())
        editor.putString("faculty", facultyEditText.text.toString())
        editor.putString("date", dateEditText.text.toString())
        // Save the photo path if available, you need to implement this logic
        editor.putString("wasteType", wasteTypeEditText.text.toString())
        editor.apply()
    }

    private fun loadFormDataFromSharedPreferences() {
        formData.location = sharedPreferences.getString("location", "") ?: ""
        formData.faculty = sharedPreferences.getString("faculty", "") ?: ""
        formData.date = sharedPreferences.getString("date", "") ?: ""
        // Load the photo if available, you need to implement this logic
        formData.wasteType = sharedPreferences.getString("wasteType", "") ?: ""

        // Update the form fields
        locationEditText.setText(formData.location)
        facultyEditText.setText(formData.faculty)
        dateEditText.setText(formData.date)
        // Load the photo into the photoImageView if available
        wasteTypeEditText.setText(formData.wasteType)
    }

    data class FormData(
        var location: String,
        var faculty: String,
        var date: String,
        var photoPath: String?, // Store the path to the photo here
        var wasteType: String
    )
}