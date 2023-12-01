package com.example.simpalaapps.model

import android.util.Log
import com.example.simpalaapps.model.ReportDao
import com.example.simpalaapps.model.ReportEntity

// ReportRepository.kt

class ReportRepository(private val reportDao: ReportDao) {

    fun getAllReports(): List<ReportEntity> {
        return reportDao.getAllReports()
    }

    fun getReportById(reportId: Long): ReportEntity? {
        return reportDao.getReportById(reportId)
    }

    fun searchReport(reportType: String?): List<ReportEntity> {
        return reportDao.searchReport(reportType)
    }

    fun insertReport(report: ReportEntity) {
        reportDao.insertReport(report)
    }

    fun insertAll(reports: List<ReportEntity>) {
        reportDao.insertAll(reports)
    }

    fun updateReport(report: ReportEntity) {
        reportDao.updateReport(report)
    }

    fun deleteReport(reportId: Long) {
        reportDao.deleteReport(reportId)
    }
}

