package com.example.simpalaapps.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "report_table")
data class ReportEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = 0,
    val reportType: String,
    val reporterName: String,
    val reportDesc: String,
    val latitude: Double,
    val longitude: Double,
    val photo: ByteArray,
    val reportingDate: String,
    val reporterEmail: String,
    val isReadOnly: Boolean
)

// For Retrofit
data class Report(
    val reportId: Long? = 0,
    val reportType: String,
    val reporterName: String,
    val reportDesc: String,
    val latitude: Double,
    val longitude: Double,
    val photo: String,
    val reportingDate: String,
    val reporterEmail: String,
    val isReadOnly: Boolean
)
data class ReportsResponse(
    val message: String,
    val reports: List<Report>
)

