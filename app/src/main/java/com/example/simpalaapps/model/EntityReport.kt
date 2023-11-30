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
    val reporterEmail: String
)

