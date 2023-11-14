package com.example.papb1.model

interface ReportModelContract {
    interface View {
        fun showReports(reports: List<Report>)
        fun showError(message: String)
    }

    interface Presenter {
        fun getReports()
        fun saveReport(report: Report)
    }

    interface Model {
        fun getReports(callback: (List<Report>?, String?) -> Unit)
        fun saveReport(report: Report, callback: (Boolean, String?) -> Unit)
    }
}
