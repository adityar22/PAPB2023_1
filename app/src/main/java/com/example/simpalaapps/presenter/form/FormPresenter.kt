package com.example.simpalaapps.presenter.form

import com.example.simpalaapps.model.ReportDao
import com.example.simpalaapps.model.ReportEntity

class FormReportPresenter(private val view: FormReportContract.View, private val reportDao: ReportDao) :
    FormReportContract.Presenter {

    override fun submitReport(report: ReportEntity) {
        // Lakukan validasi atau operasi lain yang diperlukan sebelum menyimpan laporan
        reportDao.insertReport(report)
        // Panggil metode di view jika diperlukan (misalnya, untuk memberi tahu bahwa laporan telah berhasil disubmit)
    }
}
