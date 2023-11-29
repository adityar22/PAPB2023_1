package com.example.simpalaapps.presenter

import com.example.simpalaapps.model.ReportDao

class DashboardPresenter(private val view: DashboardContract.View, private val reportDao: ReportDao) : DashboardContract.Presenter {

    override fun loadReports() {
        val reports = reportDao.getAllReports()
        view.showReports(reports)
    }
}