package com.example.simpalaapps.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.simpalaapps.model.news.NewsDao
import com.example.simpalaapps.model.news.NewsEntity

@Database(entities = [ReportEntity::class, NewsEntity::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reportDao(): ReportDao
    abstract fun newsDao(): NewsDao

    companion object {
        private var instance: AppDatabase? = null

        private val MIGRATION_1_2: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Define your migration logic here
                database.execSQL("CREATE TABLE IF NOT EXISTS new_news_table (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "newsTitle TEXT, " +
                        "newsTag TEXT, " +
                        "newsContent TEXT, " +
                        "photo TEXT, " +
                        "newsDate TEXT, " +
                        "isPremium TEXT)")

                database.execSQL("CREATE TABLE IF NOT EXISTS new_report_table (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "reportType TEXT, " +
                        "reporterName TEXT, " +
                        "reportDesc TEXT, " +
                        "latitude REAL, " +
                        "longitude REAL, " +
                        "photo BLOB, " +
                        "reportingDate TEXT, " +
                        "reporterEmail TEXT)")

                // Add a new column 'report_desc'
                // database.execSQL("ALTER TABLE report_table ADD COLUMN report_desc TEXT")
                // Drop the old table
                database.execSQL("DROP TABLE IF EXISTS report_table")
                database.execSQL("DROP TABLE IF EXISTS news_table")

                // Rename the new table to the old table name
                database.execSQL("ALTER TABLE new_report_table RENAME TO report_table")
                database.execSQL("ALTER TABLE new_news_table RENAME TO news_table")
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