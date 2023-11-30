package com.example.simpalaapps.view.report

// UpdateFormFragment.kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.simpalaapps.R
import com.example.simpalaapps.model.ReportEntity
import com.example.simpalaapps.presenter.update.UpdateFormContract
import com.example.simpalaapps.presenter.update.UpdateFormPresenter

class UpdateFormFragment : Fragment(), UpdateFormContract.View {

    private lateinit var presenter: UpdateFormContract.Presenter
    private lateinit var report: ReportEntity

    private lateinit var etReportType: EditText
    private lateinit var etReporterName: EditText
    private lateinit var etReportDescription: EditText
    private lateinit var etReporterEmail: EditText

    companion object {
        private const val ARG_REPORT_ID = "arg_report_id"
        private const val ARG_REPORT_TYPE = "arg_report_type"
        private const val ARG_REPORTER_NAME = "arg_reporter_name"
        private const val ARG_REPORT_DESCRIPTION = "arg_report_description"
        private const val ARG_REPORTER_EMAIL = "arg_reporter_email"
        // Add more constants for other fields as needed

        fun newInstance(report: ReportEntity): UpdateFormFragment {
            val fragment = UpdateFormFragment()
            val args = Bundle()
            args.putLong(ARG_REPORT_ID, report.id!!)
            args.putString(ARG_REPORT_TYPE, report.reportType)
            args.putString(ARG_REPORTER_NAME, report.reporterName)
            args.putString(ARG_REPORT_DESCRIPTION, report.reportDesc)
            args.putString(ARG_REPORTER_EMAIL, report.reporterEmail)
            // Add more arguments for other fields as needed
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update_form, container, false)

        // Initialize presenter
        presenter = UpdateFormPresenter(this)

        // Retrieve report from arguments
        val reportId = arguments?.getLong(ARG_REPORT_ID) ?: 0
        val reportType = arguments?.getString(ARG_REPORT_TYPE) ?: ""
        val reporterName = arguments?.getString(ARG_REPORTER_NAME) ?: ""
        val reportDescription = arguments?.getString(ARG_REPORT_DESCRIPTION) ?: ""
        val reporterEmail = arguments?.getString(ARG_REPORTER_EMAIL) ?: ""

        report = ReportEntity(reportId, reportType, reporterName,reportDescription, 0.0, 0.0, ByteArray(0), "", reporterEmail)
        // Create the report using the retrieved values, set default values as needed

        // Find views
        etReportType= view.findViewById(R.id.etReportType)
        etReporterName = view.findViewById(R.id.etReporterName)

        // Set default values from the report
        etReportType.setText(report.reportType)
        etReporterName.setText(report.reporterName)

        val btnUpdate: Button = view.findViewById(R.id.btnUpdate)

        btnUpdate.setOnClickListener {
            onUpdateClicked()
        }

        return view
    }

    override fun onUpdateClicked() {
        // Implement the logic to handle the update process
        // You can pass the updated values to the presenter
        val updatedReport = ReportEntity(
            report.id,
            etReportType.text.toString(),
            etReporterName.text.toString(),
            report.reportDesc,
            report.latitude,
            report.longitude,
            report.photo,
            report.reportingDate,
            report.reporterEmail
        )
        presenter.onUpdateClicked(updatedReport)
    }

    // Implement other methods from UpdateFormContract.View as needed
}
