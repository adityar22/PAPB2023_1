package com.example.simpalaapps.model.news

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = 0,
    val newsTitle: String,
    val newsExcerpt: String,
    val newsTag: String,
    val newsContent: String,
    val photo: String,
    val newsDate: String,
    val url: String,
    val isPremium: Boolean
)

data class News(
    val newsId: Long? = 0,
    val newsTitle: String,
    val newsExcerpt: String,
    val newsTag: String,
    val newsContent: String,
    val newsDate: String,
    val photo: String,
    val url: String,
    val isPremium: Boolean
)
data class NewsResponse(
    val message: String,
    val news: List<News>
)