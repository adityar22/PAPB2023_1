package com.example.simpalaapps.presenter

import android.content.Context
import com.example.simpalaapps.model.ReportDao
import com.example.simpalaapps.model.ReportEntity
import com.example.simpalaapps.model.ReportRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream

class DashboardPresenter(
    private val view: DashboardContract.View,
    private val reportDao: ReportDao,
    private val repository: ReportRepository,
    private val context: Context
) : DashboardContract.Presenter {

    // Use 'lifecycleScope' from the Fragment, or if you're in a ViewModel, use 'viewModelScope'
    private val scope = view.lifecycleScope_

    override fun loadReports() {
        // Launch a coroutine to perform the database operation on a background thread
        scope.launch {
            // Use 'withContext' to switch to the IO dispatcher for database operations
            val reports = withContext(Dispatchers.IO) {
                reportDao.getAllReports()
            }

            // Update UI on the main thread
            view.showReports(reports)
        }
    }
}
