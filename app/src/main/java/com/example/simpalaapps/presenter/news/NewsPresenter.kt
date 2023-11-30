package com.example.simpalaapps.presenter.news

import android.content.Context
import com.example.simpalaapps.model.ReportDao
import com.example.simpalaapps.model.ReportEntity
import com.example.simpalaapps.model.ReportRepository
import com.example.simpalaapps.model.news.NewsDao
import com.example.simpalaapps.model.news.NewsEntity
import com.example.simpalaapps.model.news.NewsRepository
import com.example.simpalaapps.presenter.DashboardContract
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream

class NewsPresenter(
    private val view: NewsContract.View,
    private val newsDao: NewsDao,
    private val repository: NewsRepository,
    private val context: Context
) : NewsContract.Presenter {

    // Use 'lifecycleScope' from the Fragment, or if you're in a ViewModel, use 'viewModelScope'
    private val scope = view.lifecycleScope_

    override fun loadNews() {
        // Launch a coroutine to perform the database operation on a background thread
        scope.launch {
            // Use 'withContext' to switch to the IO dispatcher for database operations
            val news = withContext(Dispatchers.IO) {
                newsDao.getAllNews()
            }

            // Update UI on the main thread
            view.showNews(news)
        }
    }

    private fun getDummyData(context: Context): List<NewsEntity> {
        val jsonString: String? = readJsonFromAssets(context,"dummy_data.json")

        return if (!jsonString.isNullOrBlank()) {
            parseJsonToNewsEntities(jsonString)
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

    private fun parseJsonToNewsEntities(jsonString: String): List<NewsEntity> {
        // Implement logic to parse JSON string to List<ReportEntity>
        // (use Gson or other JSON parsing libraries)
        val gson = Gson()
        val newsListType = object : TypeToken<List<NewsEntity>>() {}.type
        return gson.fromJson(jsonString, newsListType)
    }
}