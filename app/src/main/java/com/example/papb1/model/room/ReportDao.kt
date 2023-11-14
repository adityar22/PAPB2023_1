package com.example.papb1.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ReportDao {
    @Query("SELECT * FROM report_table")
    fun getAllReports(): List<ReportEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(reports: List<ReportEntity>)
}
