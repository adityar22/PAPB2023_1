package com.example.simpalaapps.view.report

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.example.simpalaapps.R
import com.example.simpalaapps.model.AppDatabase
import com.example.simpalaapps.model.ReportDao
import com.example.simpalaapps.model.ReportEntity
import com.example.simpalaapps.presenter.detail.DetailReportContract
import com.example.simpalaapps.presenter.detail.DetailReportPresenter
import com.example.simpalaapps.model.ReportRepository
import com.example.simpalaapps.view.DashboardFragment
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

// DetailReportFragment.kt

class DetailReportFragment : Fragment(), DetailReportContract.View {

    private lateinit var presenter: DetailReportContract.Presenter
    private var reportId: Long = 0
    private lateinit var report: ReportEntity
    private lateinit var repository: ReportRepository
    private lateinit var reportDao: ReportDao

    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var btnSeeOnMap: Button

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

        reportDao = AppDatabase.getInstance(requireContext()).reportDao()
        repository = ReportRepository(reportDao)
        presenter = DetailReportPresenter(this, repository)

        btnUpdate = view.findViewById(R.id.btnUpdate)
        btnDelete = view.findViewById(R.id.btnDelete)
        btnSeeOnMap = view.findViewById(R.id.btnSeeOnMap)

        lifecycleScope.launch {
            presenter.onViewCreated(reportId)
        }

        btnUpdate.setOnClickListener {
            openUpdateFormFragment()
        }

        btnDelete.setOnClickListener {
            showDeleteConfirmationDialog(reportId)
        }

        btnSeeOnMap.setOnClickListener{
            showMapDirect(report.latitude, report.longitude)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun showMapDirect(latitude: Double, longitude: Double){
        val geoUri = "geo:$latitude,$longitude?q=$latitude,$longitude"
        val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
        mapIntent.setPackage("com.google.android.apps.maps")

        if (mapIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(mapIntent)
        } else {
            Toast.makeText(requireContext(), "Google Maps app is not installed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openUpdateFormFragment() {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager

        val updateFormFragment = UpdateFormFragment.newInstance(
            report
        )

        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, updateFormFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun showDeleteConfirmationDialog(reportId: Long) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Delete Confirmation")
        alertDialogBuilder.setMessage("Are you sure you want to delete this report?")
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            onDeleteClicked(reportId)
            backToMainActivity()
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun showReportDetails(report: ReportEntity) {
        this.report = report

        val reportTypeTextView: TextView = requireView().findViewById(R.id.reportTypeTextView)
        val photoImageView: ImageView = requireView().findViewById(R.id.photoImageView)
        val reporterNameTextView: TextView = requireView().findViewById(R.id.reporterNameTextView)
        val locationTextView: TextView = requireView().findViewById(R.id.locationTextView)
        val reportDescriptionTextView: TextView = requireView().findViewById(R.id.reportDescriptionTextView)
        val reportingDateTextView: TextView = requireView().findViewById(R.id.reportingDateTextView)
        val reporterEmailTextView: TextView = requireView().findViewById(R.id.reporterEmailTextView)

        report?.let {
            // Set values from the ReportEntity to TextView
            reportTypeTextView.text = "Report Type: ${it.reportType}"
            reporterNameTextView.text = "Reporter Name: ${it.reporterName}"
            locationTextView.text = "Location: ${it.latitude}, ${it.longitude}"
            reportDescriptionTextView.text = "${it.reportDesc}"
            reportingDateTextView.text = "Reporting Date: ${it.reportingDate}"
            reporterEmailTextView.text = "Reporter Email: ${it.reporterEmail}"

            if (it.photo != null) {
                val bitmap: Bitmap? = it.photo.toBitmap()
                if (bitmap != null) {
                    photoImageView.setImageBitmap(bitmap)
                } else {
                    photoImageView.setImageResource(R.drawable.placeholder_image)
                }
            }else{
                photoImageView.setImageResource(R.drawable.placeholder_image)
            }
        }

        if(report.isReadOnly == true){
            btnUpdate.isVisible = false
            btnDelete.isVisible = false
        }
    }

    private fun ByteArray?.toBitmap(): Bitmap? {
        if (this == null) return null
        return BitmapFactory.decodeByteArray(this, 0, this.size)
    }

    override fun onDeleteClicked(reportId: Long) {
        presenter.onDeleteClicked(reportId)
    }

    private fun backToMainActivity () {
        val fragmentManager = requireActivity().supportFragmentManager
        val destinationFragment = DashboardFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragmentContainer, destinationFragment)
        fragmentTransaction.commit()
    }
}


