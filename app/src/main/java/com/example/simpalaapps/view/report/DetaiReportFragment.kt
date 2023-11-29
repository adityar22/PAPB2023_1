package com.example.simpalaapps.view.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.simpalaapps.R
import com.example.simpalaapps.model.AppDatabase
import com.example.simpalaapps.model.ReportEntity
import com.example.simpalaapps.presenter.detail.DetailReportContract
import com.example.simpalaapps.presenter.detail.DetailReportPresenter
import com.example.simpalaapps.presenter.detail.ReportRepository

class DetailReportFragment : Fragment(), DetailReportContract.View {

    private lateinit var presenter: DetailReportContract.Presenter
    private var reportId: Int = 0

    companion object {
        private const val ARG_REPORT_ID = "arg_report_id"

        fun newInstance(reportId: Int): DetailReportFragment {
            val fragment = DetailReportFragment()
            val args = Bundle()
            args.putInt(ARG_REPORT_ID, reportId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_report, container, false)

        reportId = arguments?.getInt(ARG_REPORT_ID) ?: 0

        val reportDao = AppDatabase.getInstance(requireContext()).reportDao()
        // Instantiate the repository (replace ReportRepository with your actual repository class)
        val repository = ReportRepository(reportDao)

        // Initialize presenter with the repository
        presenter = DetailReportPresenter(this, repository)

        presenter.onViewCreated(reportId)

        return view
    }

    override fun showReportDetails(report: ReportEntity) {
        // Update UI with the report details

        val reportTypeTextView: TextView = requireView().findViewById(R.id.reportTypeTextView)
        val reporterNameTextView: TextView = requireView().findViewById(R.id.reporterNameTextView)
        val locationTextView: TextView = requireView().findViewById(R.id.locationTextView)
        val reportingDateTextView: TextView = requireView().findViewById(R.id.reportingDateTextView)
        val reporterEmailTextView: TextView = requireView().findViewById(R.id.reporterEmailTextView)

        // Set values from the ReportEntity to TextView
        reportTypeTextView.text = "Report Type: ${report.reportType}"
        reporterNameTextView.text = "Reporter Name: ${report.reporterName}"
        locationTextView.text = "Location: ${report.latitude}, ${report.longitude}"
        reportingDateTextView.text = "Reporting Date: ${report.reportingDate}"
        reporterEmailTextView.text = "Reporter Email: ${report.reporterEmail}"
    }
}

