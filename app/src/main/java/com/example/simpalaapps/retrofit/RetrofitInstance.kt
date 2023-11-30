package com.example.simpalaapps.retrofit


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api:ReportApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api-papb.saddansyah.space/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReportApi::class.java)
    }
}