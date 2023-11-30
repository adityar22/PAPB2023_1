package com.example.simpalaapps.view.report

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.example.simpalaapps.R
import com.example.simpalaapps.model.AppDatabase
import com.example.simpalaapps.model.ReportEntity
import com.example.simpalaapps.presenter.detail.DetailReportContract
import com.example.simpalaapps.presenter.detail.DetailReportPresenter
import com.example.simpalaapps.presenter.detail.ReportRepository
import kotlinx.coroutines.launch

// DetailReportFragment.kt

class DetailReportFragment : Fragment(), DetailReportContract.View {

    private lateinit var presenter: DetailReportContract.Presenter
    private var reportId: Long = 0
    private lateinit var report: ReportEntity

    companion object {
        private const val ARG_REPORT_ID = "arg_report_id"

        fun newInstance(reportId: Long): DetailReportFragment {
            val fragment = DetailReportFragment()
            val args = Bundle()
            args.putLong(ARG_REPORT_ID, reportId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_report, container, false)

        reportId = arguments?.getLong(ARG_REPORT_ID) ?: 0

        val reportDao = AppDatabase.getInstance(requireContext()).reportDao()
        val repository = ReportRepository(reportDao)

        // Use let to perform the cast within a safe block

        presenter = DetailReportPresenter(this, repository)
        lifecycleScope.launch {
            presenter.onViewCreated(reportId)
        }


        val btnUpdate: Button = view.findViewById(R.id.btnUpdate)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)

        btnUpdate.setOnClickListener {
            openUpdateFormFragment()
        }

        btnDelete.setOnClickListener {
            showDeleteConfirmationDialog(reportId)
        }

        return view
    }

    private fun openUpdateFormFragment() {
        // Get the fragment manager
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager

        // Create an instance of the UpdateFormFragment with the report details
        val updateFormFragment = UpdateFormFragment.newInstance(
            report.id!!,
            report.reportType,
            report.reporterName
            // Pass other fields as needed...
        )

        // Replace the current fragment with the UpdateFormFragment
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, updateFormFragment)
            .addToBackStack(null)  // Optional: Adds the transaction to the back stack
            .commit()
    }

    private fun showDeleteConfirmationDialog(reportId: Long) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Delete Confirmation")
        alertDialogBuilder.setMessage("Are you sure you want to delete this report?")
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            onDeleteClicked(reportId)
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun showReportDetails(report: ReportEntity) {
        // Update UI with the report details
        this.report = report

        val reportTypeTextView: TextView = requireView().findViewById(R.id.reportTypeTextView)
        val photoImageView: ImageView = requireView().findViewById(R.id.photoImageView)
        val reporterNameTextView: TextView = requireView().findViewById(R.id.reporterNameTextView)
        val locationTextView: TextView = requireView().findViewById(R.id.locationTextView)
        val reportDescriptionTextView: TextView = requireView().findViewById(R.id.reportDescriptionTextView)
        val reportingDateTextView: TextView = requireView().findViewById(R.id.reportingDateTextView)
        val reporterEmailTextView: TextView = requireView().findViewById(R.id.reporterEmailTextView)

        // Set values from the ReportEntity to TextView
        report?.let {
            // Set values from the ReportEntity to TextView
            reportTypeTextView.text = "Report Type: ${it.reportType}"
            reporterNameTextView.text = "Reporter Name: ${it.reporterName}"
            locationTextView.text = "Location: ${it.latitude}, ${it.longitude}"
            reportDescriptionTextView.text = "${it.reportDesc}"
            reportingDateTextView.text = "Reporting Date: ${it.reportingDate}"
            reporterEmailTextView.text = "Reporter Email: ${it.reporterEmail}"

            val bitmap: Bitmap? = it.photo.toBitmap()
            photoImageView.setImageBitmap(bitmap)
        }
    }

    private fun ByteArray.toBitmap(): Bitmap {
        return BitmapFactory.decodeByteArray(this, 0, this.size)
    }

    override fun onDeleteClicked(reportId: Long) {
        // Implement the onDeleteClicked logic here
        // For example, you can pass the report to the presenter
        presenter.onDeleteClicked(reportId)
    }
}


