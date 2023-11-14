package com.example.papb1.presenter

import com.example.papb1.model.Report
import com.example.papb1.model.ReportModelContract
import com.example.papb1.ui.report.ReportFragment

class ReportPresenter(private val view: ReportModelContract.View, private val model: ReportModelContract.Model) :
    ReportModelContract.Presenter {

    override fun getReports() {
        model.getReports { reports, error ->
            if (reports != null) {
                view.showReports(reports)
            } else {
                view.showError(error ?: "Unknown error")
            }
        }
    }

    override fun saveReport(report: Report) {
        model.saveReport(report) { success, error ->
            if (success) {
                // Handle successful save
            } else {
                view.showError(error ?: "Unknown error")
            }
        }
    }
}
