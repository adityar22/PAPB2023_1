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

        // Bind data to views
        view.findViewById<TextView>(R.id.tvReportType).text = report.reportType
        view.findViewById<TextView>(R.id.tvReporterName).text = report.reporterName
        // ... bind other views

        // Example of displaying location
        val locationTextView: TextView = view.findViewById(R.id.tvLocation)
        locationTextView.text = "Location: ${report.location.latitude}, ${report.location.longitude}"

        // Example of displaying date
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateTextView: TextView = view.findViewById(R.id.tvReportingDate)
        dateTextView.text = "Date: ${dateFormat.format(report.reportingDate)}"

        // Load photo using a library like Picasso or Glide
        val photoImageView: ImageView = view.findViewById(R.id.ivPhoto)
        // Use a library like Picasso or Glide to load the image from the URL or file path
        // Picasso.get().load(report.photo).into(photoImageView)
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


    data class FormData(
        var location: String,
        var faculty: String,
        var date: String,
        var photoPath: String?, // Store the path to the photo here
        var wasteType: String
    )
}