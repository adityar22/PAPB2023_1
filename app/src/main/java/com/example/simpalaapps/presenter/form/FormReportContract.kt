package com.example.simpalaapps.presenter.form

import com.example.simpalaapps.model.ReportEntity

interface FormReportContract {
    interface View {
        // Tambahkan metode yang diperlukan untuk respons UI di sini
    }

    interface Presenter {
        suspend fun submitReport(report: ReportEntity)
    }
}
