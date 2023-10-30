package com.example.papb1

import android.app.Activity
import android.os.Bundle
import com.example.papb1.model.ReportModel
import com.example.papb1.presenter.ReportPresenter
import com.example.papb1.ui.report.ReportFragment

interface ReportView {
    // Metode yang akan dipanggil oleh Presenter untuk mengupdate tampilan
    fun showReport(report: List<ReportModel>)
}
class ReportActivity : Activity(), ReportView {
    private val fragment = ReportFragment()
    private val presenter = ReportPresenter(fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Memanggil metode presenter untuk mendapatkan dan menampilkan data
        presenter.getPeople()
    }

    override fun showReport(report: List<ReportModel>) {
        // Menampilkan informasi orang-orang di tampilan (misalnya, RecyclerView)
        for (report in report) {
            println("ID: ${report.id}, Subject: ${report.subject}, Reported By: ${report.reportBy}")
        }
    }
}
