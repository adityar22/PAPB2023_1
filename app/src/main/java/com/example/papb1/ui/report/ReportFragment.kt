package com.example.papb1.ui.report
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.papb1.R
import com.example.papb1.ReportView
import com.example.papb1.model.ReportModel
import com.example.papb1.presenter.ReportPresenter

class ReportFragment : Fragment(), ReportView {
    private val presenter = ReportPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_report, container, false)

        // Memanggil metode presenter untuk mendapatkan dan menampilkan data
        presenter.getPeople()

        return view
    }

    override fun showReport(report: List<ReportModel>) {
        // Menampilkan informasi orang-orang di tampilan (misalnya, RecyclerView)
        for (report in report) {
            println("ID: ${report.id}, Subject: ${report.subject}, Reported By: ${report.reportBy}")
        }
    }
}
