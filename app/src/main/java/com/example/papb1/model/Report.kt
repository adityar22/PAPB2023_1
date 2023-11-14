package com.example.papb1.model

import java.util.Date

data class Report(
    val id: Int,
    val reportType: String,
    val reporterName: String,
    val location: Location,
    val photo: String, // Assuming photo is a URL or file path
    val reportingDate: Date,
    val reporterEmail: String
)

data class Location(
    val latitude: Double,
    val longitude: Double
)