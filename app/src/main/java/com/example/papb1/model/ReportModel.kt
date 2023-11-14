package com.example.papb1.model

import com.example.papb1.model.room.ReportDao
import com.example.papb1.model.room.ReportEntity
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import java.util.Date

class FirebaseReportModel(private val reportDao: ReportDao) : ReportModelContract.Model {

    private val firestore = FirebaseFirestore.getInstance()
    private val reportsCollection = firestore.collection("reports")

    override fun getReports(callback: (List<Report>?, String?) -> Unit) {
        // Try to fetch data from Room database first
        val localReports = reportDao.getAllReports()
        if (localReports.isNotEmpty()) {
            callback(localReports.map { it.toReport() }, null)
        } else {
            // If local data is not available, fetch data from Firebase
            reportsCollection.get()
                .addOnSuccessListener { result ->
                    val reports = result.toObjects(ReportEntity::class.java)
                    // Save the fetched data to Room database
                    reportDao.insertAll(reports)
                    callback(reports.map { it.toReport() }, null)
                }
                .addOnFailureListener { exception ->
                    callback(null, exception.message)
                }
        }
    }

    override fun saveReport(report: Report, callback: (Boolean, String?) -> Unit) {
        val reportEntity = ReportEntity(
            report.id,
            report.reportType,
            report.reporterName,
            report.location.latitude,
            report.location.longitude,
            report.photo,
            report.reportingDate,
            report.reporterEmail
        )

        // Save the report to Firebase
        reportsCollection.document(report.id.toString())
            .set(reportEntity)
            .addOnSuccessListener {
                // Save the report to Room database after successful save to Firebase
                reportDao.insertAll(listOf(reportEntity))
                callback(true, null)
            }
            .addOnFailureListener { exception ->
                callback(false, exception.message)
            }
    }

    private fun ReportEntity.toReport(): Report {
        return Report(
            id = id,
            reportType = "", // Assuming 'subject' is the reportType in the ReportEntity
            reporterName = "",
            location = Location(latitude = 0.0, longitude = 0.0), // You need to fetch the location from Firebase
            photo = "", // You need to fetch the photo URL or file path from Firebase
            reportingDate = Date(), // You need to fetch the reporting date from Firebase
            reporterEmail = "" // You need to fetch the reporter email from Firebase
        )
    }
}

