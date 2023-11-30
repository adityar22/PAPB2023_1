package com.example.simpalaapp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Query

@Dao
interface ReportDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReport(report: ReportEntity)

    @Update
    fun updateReport(report: ReportEntity)

    @Delete
    fun deleteReport(report: ReportEntity)

    @Query("SELECT * from report_table")
    fun getReports(): List<Report>

}