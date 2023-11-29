package com.example.simpalaapps.presenter.detail

import com.example.simpalaapps.model.ReportEntity


// DetailReportPresenter.kt

class DetailReportPresenter(
    private val view: DetailReportContract.View,
    private val repository: ReportRepository
) : DetailReportContract.Presenter {

    private lateinit var report: ReportEntity

    override fun onViewCreated(reportId: Int) {
        // Load report details from the repository based on reportId
        repository.getReportById(reportId)?.let {
            report = it
            view.showReportDetails(report)
        }
    }

    override fun onDeleteClicked(reportId: Int) {
        // Handle delete button click
        repository.deleteReport(reportId)
        // You may want to navigate back to the previous screen or show a success message
    }
}

