package com.example.papb1.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.papb1.R
import com.example.papb1.model.Location
import com.example.papb1.model.Report
import com.example.papb1.presenter.ReportPresenter
import java.util.Date

class DashboardFragment : Fragment() {
    private lateinit var presenter: ReportPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_report_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etReportType = view.findViewById<EditText>(R.id.etReportType)
        val etReporterName = view.findViewById<EditText>(R.id.etReporterName)
        // Initialize other views

        val btnSubmitReport = view.findViewById<Button>(R.id.btnSubmitReport)
        btnSubmitReport.setOnClickListener {
            val reportType = etReportType.text.toString()
            val reporterName = etReporterName.text.toString()
            // Get values from other fields

            // Example: Hardcoded location for testing
            val location = Location(37.7749, -122.4194)

            // Example: Hardcoded date for testing
            val reportingDate = Date()

            // Example: Hardcoded email for testing
            val reporterEmail = "example@example.com"

            // Example: Hardcoded photo URL for testing
            val photoUrl = "https://example.com/photo.jpg"

            val newReport = Report(0, reportType, reporterName, location, photoUrl, reportingDate, reporterEmail)
            presenter.saveReport(newReport)
        }
    }
}
