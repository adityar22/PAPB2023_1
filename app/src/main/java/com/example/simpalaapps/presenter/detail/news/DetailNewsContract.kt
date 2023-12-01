package com.example.simpalaapps.presenter.detail.news

import com.example.simpalaapps.model.news.NewsEntity

interface DetailNewsContract {
    interface Presenter {
        suspend fun onViewCreated(newsId: Long)
        fun onDeleteClicked(newsId: Long)
    }

    interface View {
        fun showNewsDetails(news: NewsEntity)
    }
}