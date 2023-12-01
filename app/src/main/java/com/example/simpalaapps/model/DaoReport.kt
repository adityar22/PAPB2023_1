package com.example.simpalaapps.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ReportDao {
    @Query("SELECT * FROM report_table")
    fun getAllReports(): List<ReportEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReport(report: ReportEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(reports: List<ReportEntity>): List<Long>

    @Query("SELECT * FROM report_table WHERE id = :reportId")
    fun getReportById(reportId: Long): ReportEntity

    @Update
    fun updateReport(report: ReportEntity): Int

    @Query("SELECT * FROM report_table WHERE reportType LIKE :reportType")
    fun searchReport(reportType: String?): List<ReportEntity>

    @Query("DELETE FROM report_table WHERE id = :reportId")
    fun deleteReport(reportId: Long): Int


}
