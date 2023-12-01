package com.example.simpalaapps.model.news

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table")
    fun getAllNews(): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: NewsEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<NewsEntity>): List<Long>

    @Query("SELECT * FROM news_table WHERE id = :newsId")
    fun getNewsById(newsId: Long): NewsEntity

    @Update
    fun updateNews(news: NewsEntity): Int

    @Query("DELETE FROM news_table WHERE id = :newsId")
    fun deleteNews(newsId: Long): Int

}