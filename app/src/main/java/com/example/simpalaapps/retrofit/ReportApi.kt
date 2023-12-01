package com.example.simpalaapps.retrofit

import com.example.simpalaapps.model.ReportsResponse
import com.example.simpalaapps.model.news.NewsResponse
import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {
    @GET("reports")
    fun getReportsFromApi():Call<ReportsResponse>

    @GET("news")
    fun getNewsFromApi(): Call<NewsResponse>
}