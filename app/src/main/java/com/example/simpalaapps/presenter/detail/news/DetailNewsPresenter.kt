package com.example.simpalaapps.presenter.detail.news

import com.example.simpalaapps.model.news.NewsEntity
import com.example.simpalaapps.model.news.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailNewsPresenter(
    private val view: DetailNewsContract.View,
    private val repository: NewsRepository
) : DetailNewsContract.Presenter {

    private lateinit var news: NewsEntity

    override suspend fun onViewCreated(newsId: Long) {
        // Load report details from the repository based on reportId
        news = withContext(Dispatchers.IO) {
            repository.getNewsById(newsId)!!
        }
        news?.let {
            view.showNewsDetails(it)
        }
    }

    override fun onDeleteClicked(newsId: Long) {
        // Handle delete button click
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteReport(newsId)
            // You may want to navigate back to the previous screen or show a success message
        }
        // You may want to navigate back to the previous screen or show a success message
    }
}