package com.example.papb1

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.papb1.model.FirebaseReportModel
import com.example.papb1.model.Location
import com.example.papb1.model.Report
import com.example.papb1.model.ReportModelContract
import com.example.papb1.presenter.ReportPresenter
import com.example.papb1.ui.report.ReportFragment
import java.util.Date

class ReportActivity : AppCompatActivity(), ReportModelContract.View {

    private val reportDao by lazy { (application as SimpalaRoom).reportDatabase.reportDao() }
    private val presenter: ReportModelContract.Presenter = ReportPresenter(this, FirebaseReportModel(reportDao))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        // Example: Trigger fetching reports
        presenter.getReports()

        val reportDatabase = (application as SimpalaRoom).reportDatabase

        // Example: Trigger saving a report
        val report = Report(
            id = 4,
            reportType = "New Report",
            reporterName = "John Doe",
            location = Location(latitude = 12.345, longitude = 67.890), // Provide actual latitude and longitude values
            photo = "url_or_file_path.jpg", // Provide actual URL or file path
            reportingDate = Date(), // You can initialize it with the current date or use a specific date
            reporterEmail = "john.doe@example.com"
        )

        presenter.saveReport(report)

        // Display the ReportFragment
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(
            R.id.fragmentContainer,
            ReportFragment.newInstance(report)
        )
        fragmentTransaction.commit()
    }

    override fun showReports(reports: List<Report>) {
        // Update UI with the list of reports
    }

    override fun showError(message: String) {
        // Show error message to the user
    }
}

