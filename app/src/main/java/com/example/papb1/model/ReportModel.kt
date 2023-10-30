package com.example.papb1.model

data class ReportModel(val id: Int, val subject: String, val reportBy: String)

class Report{
    fun getReport(): List<ReportModel>{
        return listOf(
            ReportModel(1, "Sampah Berserak di Selokan", "Sumono"),
            ReportModel(2, "Sampah Berserak di Jalan", "Sumiyati"),
            ReportModel(3, "TPS Rusak", "Jhon")
        )
    }
}
