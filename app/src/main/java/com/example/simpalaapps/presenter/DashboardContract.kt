package com.example.simpalaapps.presenter

import com.example.simpalaapps.model.ReportEntity

interface DashboardContract {

    interface View {
        fun showReports(reports: List<ReportEntity>)
        // Tambahkan metode lain sesuai kebutuhan
    }

    interface Presenter {
        fun loadReports()
        // Tambahkan metode lain sesuai kebutuhan
    }
}