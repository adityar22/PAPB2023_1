package com.example.simpalaapps.presenter

import android.content.Context
import com.example.simpalaapps.model.Report
import com.example.simpalaapps.model.ReportDao
import com.example.simpalaapps.model.ReportEntity
import com.example.simpalaapps.model.ReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardPresenter(
    private val view: DashboardContract.View,
    private val reportDao: ReportDao,
    private val repository: ReportRepository,
    private val context: Context
) : DashboardContract.Presenter {

    private val scope = view.lifecycleScope_

    // Using coroutines to use other thread
    override fun loadReports() {
        scope.launch {
            val reports = withContext(Dispatchers.IO) {
                repository.getAllReports()
            }
            view.showReports(reports)
        }
    }

    override fun searchReport(query: String) {
        scope.launch {
            val reports = withContext(Dispatchers.IO) {
                repository.searchReport("%$query%")
            }

            view.showReports(reports)
        }
    }

    override fun insertAllReport(reports: List<ReportEntity>) {
        scope.launch {
            withContext(Dispatchers.IO) {
                repository.insertAll(reports)
            }
        }
    }


}
