package com.example.simpalaapps.presenter.detail

import com.example.simpalaapps.model.ReportEntity

interface DetailReportContract {
    interface Presenter {
        suspend fun onViewCreated(reportId: Long)
        fun onDeleteClicked(reportId: Long)
    }

    interface View {
        fun showReportDetails(report: ReportEntity)
        fun onDeleteClicked(reportId: Long)
    }
}

