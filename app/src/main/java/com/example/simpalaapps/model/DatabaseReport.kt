package com.example.simpalaapps.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [ReportEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reportDao(): ReportDao

    companion object {
        private var instance: AppDatabase? = null

        private val MIGRATION_1_2: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Define your migration logic here
                database.execSQL("CREATE TABLE IF NOT EXISTS new_report_table (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "reportType TEXT, " +
                        "reporterName TEXT, " +
                        "reportDesc TEXT, " +
                        "latitude REAL, " +
                        "longitude REAL, " +
                        "photo BLOB, " +  // Menggunakan BLOB untuk menyimpan array byte (ByteArray)
                        "reportingDate TEXT, " +
                        "reporterEmail TEXT)")

                // Add a new column 'report_desc'
                database.execSQL("ALTER TABLE report_table ADD COLUMN report_desc TEXT")
                // Drop the old table
                database.execSQL("DROP TABLE IF EXISTS report_table")

                // Rename the new table to the old table name
                database.execSQL("ALTER TABLE new_report_table RENAME TO report_table")
            }
        }
        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                .addMigrations(MIGRATION_1_2)  // Use addMigrations instead of addMigration
                .build()
        }
    }
}