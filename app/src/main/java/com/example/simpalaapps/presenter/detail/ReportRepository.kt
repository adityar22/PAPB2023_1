package com.example.simpalaapps.presenter.detail

import com.example.simpalaapps.model.ReportDao
import com.example.simpalaapps.model.ReportEntity

// ReportRepository.kt

class ReportRepository(private val reportDao: ReportDao) {

    fun getReportById(reportId: Long): ReportEntity? {
        return reportDao.getReportById(reportId)
    }

    fun insertReport(report: ReportEntity) {
        reportDao.insertReport(report)
    }

//    suspend fun insertAll(reports: List<ReportEntity>) {
//        reportDao.insertAll(reports)
//    }

    fun updateReport(report: ReportEntity) {
        reportDao.updateReport(report)
    }

    fun deleteReport(reportId: Long) {
        reportDao.deleteReport(reportId)
    }
}
