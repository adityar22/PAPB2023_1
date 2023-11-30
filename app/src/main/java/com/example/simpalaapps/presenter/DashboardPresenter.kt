package com.example.simpalaapps.presenter

import android.content.Context
import com.example.simpalaapps.model.ReportDao
import com.example.simpalaapps.model.ReportEntity
import com.example.simpalaapps.presenter.detail.ReportRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
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

//    override fun injectDummyData() {
//        CoroutineScope(Dispatchers.IO).launch {
//            val dummyData = getDummyData(context)
//            repository.insertAll(dummyData)
//            loadReports()
//        }
//    }

    private fun getDummyData(context: Context): List<ReportEntity> {
        val jsonString: String? = readJsonFromAssets(context,"dummy_data.json")

        return if (!jsonString.isNullOrBlank()) {
            parseJsonToReportEntities(jsonString)
        } else {
            // Handle the case when jsonString is null or empty
            emptyList()
        }
    }

    private fun readJsonFromAssets(context: Context, fileName: String): String? {
        try {
            val inputStream: InputStream = context.assets.open(fileName)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            return String(buffer, Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    private fun parseJsonToReportEntities(jsonString: String): List<ReportEntity> {
        // Implement logic to parse JSON string to List<ReportEntity>
        // (use Gson or other JSON parsing libraries)
        val gson = Gson()
        val reportListType = object : TypeToken<List<ReportEntity>>() {}.type
        return gson.fromJson(jsonString, reportListType)
    }
}
