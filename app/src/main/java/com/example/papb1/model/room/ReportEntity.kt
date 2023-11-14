package com.example.papb1.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "report_table")
data class ReportEntity(
    @PrimaryKey val id: Int,
    val reportType: String,
    val reporterName: String,
    val latitude: Double,
    val longitude: Double,
    val photo: String,
    val reportingDate: Date,
    val reporterEmail: String
)

