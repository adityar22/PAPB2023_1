package com.example.simpalaapps.presenter.news

import com.example.simpalaapps.model.news.NewsEntity
import kotlinx.coroutines.CoroutineScope

interface NewsContract {

    interface View {
        val lifecycleScope_: CoroutineScope
        fun showNews(news: List<NewsEntity>)
    }

    interface Presenter {
        fun loadNews()
    }
}