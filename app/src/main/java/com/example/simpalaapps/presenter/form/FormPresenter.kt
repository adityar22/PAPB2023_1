package com.example.simpalaapps.presenter.form

import android.util.Log
import com.example.simpalaapps.model.ReportDao
import com.example.simpalaapps.model.ReportEntity
import com.example.simpalaapps.model.ReportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FormReportPresenter(private val view: FormReportContract.View, private val reportDao: ReportDao, private val repository: ReportRepository) :
    FormReportContract.Presenter {

    override suspend fun submitReport(report: ReportEntity) {
        withContext(Dispatchers.IO) {
            repository.insertReport(report)
        }
    }
}
