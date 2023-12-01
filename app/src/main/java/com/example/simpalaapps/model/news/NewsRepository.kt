package com.example.simpalaapps.model.news

import com.example.simpalaapps.model.ReportEntity

class NewsRepository(private val newsDao: NewsDao) {

    fun getAllNews(): List<NewsEntity> {
        return newsDao.getAllNews()
    }

    fun getNewsById(newsId: Long): NewsEntity? {
        return newsDao.getNewsById(newsId)
    }

    fun deleteReport(newsId: Long) {
        newsDao.deleteNews(newsId)
    }

    // Not yet implemented
    fun insertNews(news: NewsEntity) {
        newsDao.insertNews(news)
    }

    fun insertAll(news: List<NewsEntity>) {
        newsDao.insertAll(news)
    }

    fun updateNews(news: NewsEntity) {
        newsDao.updateNews(news)
    }
}