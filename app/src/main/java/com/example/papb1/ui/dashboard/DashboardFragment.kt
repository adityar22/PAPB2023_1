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
import android.widget.TextView
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.papb1.R
import com.example.papb1.databinding.FragmentDashboardBinding
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.text.Editable
import java.text.SimpleDateFormat
import java.util.Date
import androidx.fragment.app.FragmentActivity
import com.example.papb1.ui.dashboard.DashboardViewModel


class DashboardFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var locationEditText: EditText
    private lateinit var facultyEditText: EditText
    private lateinit var dateEditText: EditText
    private lateinit var wasteTypeEditText: EditText
    private lateinit var textDashboard: TextView
    private lateinit var photoPreview: ImageView
    private lateinit var btnAddPhoto: Button
    private lateinit var submitButton: Button

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    // Dummy data (replace with your actual data source)
    private var formData: FormData = FormData("", "", "", null, "")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_dashboard, container, false)
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sharedPreferences = requireContext().getSharedPreferences("FormData", Context.MODE_PRIVATE)

        textDashboard = binding.textDashboard
        dateEditText = binding.etDate
        facultyEditText = binding.etFaculty
        locationEditText = binding.etLocation
        wasteTypeEditText = binding.etWasteType
        photoPreview = binding.ivPhotoPreview
        btnAddPhoto = binding.btnAddPhoto
        submitButton = binding.btnSubmit

        dashboardViewModel.text.observe(viewLifecycleOwner) {
            facultyEditText.setText("Teknik")
            dateEditText.setText("2023-09-27")
            locationEditText.setText("Jalan Grafika")
            wasteTypeEditText.setText("Anorganik")
            photoPreview.setBackgroundColor(Color.BLUE)

        }

        btnAddPhoto.setOnClickListener {
            // Implement photo capture logic here
        }

        // Implement submission logic (e.g., save report data to a database)
        submitButton.setOnClickListener {
            // Implement report submission logic here

            // Save form data to SharedPreferences
            saveFormDataToSharedPreferences()
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        // Reload form data from SharedPreferences
        loadFormDataFromSharedPreferences()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        // Save form data to SharedPreferences when the fragment is destroyed
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