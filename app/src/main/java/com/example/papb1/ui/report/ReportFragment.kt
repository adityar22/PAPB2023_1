package com.example.papb1.ui.report
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.papb1.R
import com.example.papb1.model.Report
import com.example.papb1.presenter.ReportPresenter
import java.text.SimpleDateFormat
import java.util.Locale

class ReportFragment : Fragment() {
    private lateinit var report: Report

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_report, container, false)
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

    companion object {
        fun newInstance(report: Report): ReportFragment {
            val fragment = ReportFragment()
            fragment.report = report
            return fragment
        }
    }
}

