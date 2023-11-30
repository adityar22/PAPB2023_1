package com.example.simpalaapps.retrofit

import com.example.simpalaapps.model.ReportsResponse
import retrofit2.Call
import retrofit2.http.GET


interface ReportApi {
    @GET("reports")
    fun getReportsFromApi():Call<ReportsResponse>
}