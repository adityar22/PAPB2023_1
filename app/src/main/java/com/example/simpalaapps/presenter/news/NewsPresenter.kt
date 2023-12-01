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
    private val repository: NewsRepository,
    private val context: Context
) : NewsContract.Presenter {

    private val scope = view.lifecycleScope_

    override fun loadNews() {
        scope.launch {
            val news = withContext(Dispatchers.IO) {
                repository.getAllNews()
            }
            view.showNews(news)
        }
    }
}