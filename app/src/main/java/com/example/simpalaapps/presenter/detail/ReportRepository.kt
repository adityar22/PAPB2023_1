package com.example.simpalaapps.presenter.detail

import com.example.simpalaapps.model.ReportDao
import com.example.simpalaapps.model.ReportEntity

class ReportRepository(private val reportDao: ReportDao) {

    // Assume you have a DAO for data access
    fun getReportById(reportId: Int): ReportEntity {
        return reportDao.getReportById(reportId)
    }
}
