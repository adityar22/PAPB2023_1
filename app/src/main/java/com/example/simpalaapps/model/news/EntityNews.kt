package com.example.simpalaapps.model.news

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "news_table")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = 0,
    val newsTitle: String,
    val newsTag: String,
    val newsContent: String,
    val photo: ByteArray,
    val newsDate: String,
    val isPremium: Boolean
)