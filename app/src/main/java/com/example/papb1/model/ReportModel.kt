package com.example.papb1.model

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
class FirebaseReportModel : ReportModelContract.Model {

    private val firestore = FirebaseFirestore.getInstance()
    private val reportsCollection = firestore.collection("reports")

    override fun getReports(callback: (List<Report>?, String?) -> Unit) {
        reportsCollection.get()
            .addOnSuccessListener { result ->
                val reports = result.toObjects(Report::class.java)
                callback(reports, null)
            }
            .addOnFailureListener { exception ->
                callback(null, exception.message)
            }
    }

    override fun saveReport(report: Report, callback: (Boolean, String?) -> Unit) {
        reportsCollection.document(report.id.toString())
            .set(report)
            .addOnSuccessListener {
                callback(true, null)
            }
            .addOnFailureListener { exception ->
                callback(false, exception.message)
            }
    }
}

