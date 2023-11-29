package com.example.simpalaapps.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "report_table")
data class ReportEntity(
    @PrimaryKey val id: Int,
    val reportType: String,
    val reporterName: String,
    val latitude: Double,
    val longitude: Double,
    val photo: String,
    val reportingDate: String,
    val reporterEmail: String
)
