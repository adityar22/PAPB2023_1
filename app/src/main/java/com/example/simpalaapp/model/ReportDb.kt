package com.example.simpalaapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ReportEntity::class], version = 1)
abstract class ReportDb: RoomDatabase() {
    abstract fun reportDao(): ReportDao

    companion object {
        @Volatile private var instance: ReportDb? = null
        private val LOCK = Any()

        operator fun invoke(ctx: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(ctx).also {
                instance = it
            }
        }

        private fun buildDatabase(ctx: Context): ReportDb = Room.databaseBuilder(
            ctx.applicationContext,
            ReportDb::class.java,
            "reports.db"
        ).build()
    }

}