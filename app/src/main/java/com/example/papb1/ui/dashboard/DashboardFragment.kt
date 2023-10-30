package com.example.papb1.ui.dashboard

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

class DashboardFragment : Fragment() {


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
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("FormData", Context.MODE_PRIVATE)

        // Handle photo capture (you need to implement this)
        val btnAddPhoto: Button = view.findViewById(R.id.btnAddPhoto)
        btnAddPhoto.setOnClickListener {
            // Implement photo capture logic here
        }

        // Implement submission logic (e.g., save report data to a database)
        val locationEditText: EditText = view.findViewById(R.id.etLocation)
        val facultyEditText: EditText = view.findViewById(R.id.etFaculty)
        val dateEditText: EditText = view.findViewById(R.id.etDate)
//        val photoImageView: ImageView = view.findViewById(R.id.ivPhotoPreview)
        val wasteTypeEditText: EditText = view.findViewById(R.id.etWasteType)
        val btnSubmit: Button = view.findViewById(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            // Implement report submission logic here

            // Save form data to SharedPreferences
            saveFormDataToSharedPreferences(
                locationEditText.text.toString(),
                facultyEditText.text.toString(),
                dateEditText.text.toString(),
                // Save the photo path if available, you need to implement this logic
                wasteTypeEditText.text.toString()
            )
        }
        return root
    }

    override fun onResume() {
        super.onResume()
        // Reload form data from SharedPreferences
        loadFormDataFromSharedPreferences()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Save form data to SharedPreferences when the fragment is destroyed

        saveFormDataToSharedPreferences(
            location = view?.findViewById<EditText>(R.id.etLocation)?.text.toString(),
            faculty = view?.findViewById<EditText>(R.id.etFaculty)?.text.toString(),
            date = view?.findViewById<EditText>(R.id.etDate)?.text.toString(),
            wasteType = view?.findViewById<EditText>(R.id.etWasteType)?.text.toString()
        )
    }

    private fun saveFormDataToSharedPreferences(
        location: String,
        faculty: String,
        date: String,
        wasteType: String
    ) {
        val editor = sharedPreferences.edit()
        editor.putString("location", location)
        editor.putString("faculty", faculty)
        editor.putString("date", date)
        // Save the photo path if available, you need to implement this logic
        editor.putString("wasteType", wasteType)
        editor.apply()
    }

    private fun loadFormDataFromSharedPreferences() {
        formData.location = sharedPreferences.getString("location", "") ?: ""
        formData.faculty = sharedPreferences.getString("faculty", "") ?: ""
        formData.date = sharedPreferences.getString("date", "") ?: ""
        // Load the photo if available, you need to implement this logic
        formData.wasteType = sharedPreferences.getString("wasteType", "") ?: ""

        view?.findViewById<EditText>(R.id.etLocation)?.setText(formData.location)
        view?.findViewById<EditText>(R.id.etFaculty)?.setText(formData.faculty)
        view?.findViewById<EditText>(R.id.etDate)?.setText(formData.date)
        // Load the photo into the photoImageView if available
        view?.findViewById<EditText>(R.id.etWasteType)?.setText(formData.wasteType)
    }

    data class FormData(
        var location: String,
        var faculty: String,
        var date: String,
        var photoPath: String?, // Store the path to the photo here
        var wasteType: String
    )
}