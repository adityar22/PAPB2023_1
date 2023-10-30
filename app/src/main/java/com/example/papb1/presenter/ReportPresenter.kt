package com.example.papb1.presenter

import com.example.papb1.model.Report
import com.example.papb1.ui.report.ReportFragment

class ReportPresenter(private val view: ReportFragment) {
    private val model = Report()

    // Metode ini dipanggil oleh View untuk mendapatkan data dan mengupdate tampilan
    fun getPeople() {
        val report = model.getReport()
        view.showReport(report)
    }
}