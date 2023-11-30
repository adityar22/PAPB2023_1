package com.example.simpalaapp.retrofit

import com.example.simpalaapp.model.Reports
import retrofit2.Call
import retrofit2.http.GET


interface ReportApi {

    @GET("reports")
    fun getReportsFromApi():Call<Reports>
}