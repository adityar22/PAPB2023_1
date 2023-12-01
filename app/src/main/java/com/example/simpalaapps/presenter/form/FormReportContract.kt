package com.example.simpalaapps.presenter.form

import com.example.simpalaapps.model.ReportEntity

interface FormReportContract {
    interface View {

    }

    interface Presenter {
        suspend fun submitReport(report: ReportEntity)
    }
}
