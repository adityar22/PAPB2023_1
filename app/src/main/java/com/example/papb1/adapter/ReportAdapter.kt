package com.example.papb1.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.papb1.R
import com.example.papb1.model.Report

class ReportAdapter(private val reports: List<Report>) : RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_report, parent, false)
        return ReportViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val report = reports[position]
        holder.bind(report)
    }

    override fun getItemCount(): Int {
        return reports.size
    }

    class ReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val subjectTextView: TextView = itemView.findViewById(R.id.textViewSubject)
        private val reportByTextView: TextView = itemView.findViewById(R.id.textViewReportBy)

        fun bind(report: Report) {
            subjectTextView.text = report.reportType
            reportByTextView.text = "Report by: ${report.reporterName}"
        }
    }
}
