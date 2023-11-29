package com.example.simpalaapps.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
    interface ReportDao {
        @Query("SELECT * FROM report_table")
        fun getAllReports(): List<ReportEntity>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertReport(report: ReportEntity)

        @Query("SELECT * FROM report_table WHERE id = :reportId")
        fun getReportById(reportId: Int): ReportEntity

        @Update
        fun updateReport(report: ReportEntity)

        @Query("DELETE FROM report_table WHERE id = :reportId")
        fun deleteReport(reportId: Int)
    }