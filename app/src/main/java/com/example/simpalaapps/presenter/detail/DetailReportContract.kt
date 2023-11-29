package com.example.simpalaapps.presenter.detail

import com.example.simpalaapps.model.ReportEntity

interface DetailReportContract {
    interface Presenter {
        fun onViewCreated(reportId: Int)
    }

    interface View {
        fun showReportDetails(report: ReportEntity)
    }
}

