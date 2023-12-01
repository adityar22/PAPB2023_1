package com.example.simpalaapps.presenter.detail

import com.example.simpalaapps.model.ReportEntity
import com.example.simpalaapps.model.ReportRepository
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
        report = withContext(Dispatchers.IO) {
            repository.getReportById(reportId)!!
        }
        report?.let {
            view.showReportDetails(it)
        }
    }

    override fun onDeleteClicked(reportId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteReport(reportId)
        }
    }
}

