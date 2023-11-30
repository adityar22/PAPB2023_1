package com.example.simpalaapps.presenter.detail

import com.example.simpalaapps.model.ReportEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


// DetailReportPresenter.kt

class DetailReportPresenter(
    private val view: DetailReportContract.View,
    private val repository: ReportRepository
) : DetailReportContract.Presenter {

    private lateinit var report: ReportEntity

    override suspend fun onViewCreated(reportId: Long) {
        // Load report details from the repository based on reportId
        report = withContext(Dispatchers.IO) {
            repository.getReportById(reportId)!!
        }
        report?.let {
            view.showReportDetails(it)
        }
    }

    override fun onDeleteClicked(reportId: Long) {
        // Handle delete button click
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteReport(reportId)
            // You may want to navigate back to the previous screen or show a success message
        }
        // You may want to navigate back to the previous screen or show a success message
    }
}

