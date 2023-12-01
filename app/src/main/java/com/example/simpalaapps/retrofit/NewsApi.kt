package com.example.simpalaapps.retrofit

import com.example.simpalaapps.model.news.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface NewsApi {
    @GET("news")
    fun getNewsFromApi(): Call<NewsResponse>
}