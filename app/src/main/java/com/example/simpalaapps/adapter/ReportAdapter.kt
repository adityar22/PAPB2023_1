package com.example.simpalaapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simpalaapps.R
import com.example.simpalaapps.model.ReportEntity

class ReportAdapter(private val reports: List<ReportEntity>) :
    RecyclerView.Adapter<ReportAdapter.ReportViewHolder>() {

    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(report: ReportEntity)
        fun onViewDetailClick(report: ReportEntity)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

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

    inner class ReportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textReportType: TextView = itemView.findViewById(R.id.textReportType)
        private val textReporterName: TextView = itemView.findViewById(R.id.textReporterName)
        private val textReportingDate: TextView = itemView.findViewById(R.id.textReportingDate)
        private val buttonViewDetail: Button = itemView.findViewById(R.id.buttonViewDetail)

        fun bind(report: ReportEntity) {
            textReportType.text = report.reportType
            textReporterName.text = report.reporterName
            textReportingDate.text = report.reportingDate

            itemView.setOnClickListener {
                listener?.onItemClick(report)
            }

            buttonViewDetail.setOnClickListener {
                listener?.onViewDetailClick(report)
            }
        }
    }

}

