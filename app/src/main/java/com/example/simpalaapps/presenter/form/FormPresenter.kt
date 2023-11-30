package com.example.simpalaapps.presenter.form

import android.util.Log
import com.example.simpalaapps.model.ReportDao
import com.example.simpalaapps.model.ReportEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FormReportPresenter(private val view: FormReportContract.View, private val reportDao: ReportDao) :
    FormReportContract.Presenter {

    override suspend fun submitReport(report: ReportEntity) {
        // Lakukan validasi atau operasi lain yang diperlukan sebelum menyimpan laporan
        Log.i(report.reportType, report.reportType)
        withContext(Dispatchers.IO) {
            reportDao.insertReport(report)
        }
        // Panggil metode di view jika diperlukan (misalnya, untuk memberi tahu bahwa laporan telah berhasil disubmit)
    }
}
