package com.example.papb1

import android.app.Application
import com.example.papb1.model.room.ReportDatabase

class SimpalaRoom : Application() {
    val reportDatabase by lazy { ReportDatabase.getDatabase(this) }
}