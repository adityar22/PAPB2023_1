package com.example.simpalaapps.presenter

import com.example.simpalaapps.model.ReportEntity
import kotlinx.coroutines.CoroutineScope

interface DashboardContract {

    interface View {
        val lifecycleScope_: CoroutineScope
        fun showReports(reports: List<ReportEntity>)
    }

    interface Presenter {
        fun injectDummyData()
        fun loadReports()
    }
}