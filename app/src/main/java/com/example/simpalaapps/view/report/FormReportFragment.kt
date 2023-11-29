package com.example.simpalaapps.view.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.simpalaapps.R
import com.example.simpalaapps.model.AppDatabase
import com.example.simpalaapps.model.ReportEntity
import com.example.simpalaapps.presenter.form.FormReportContract
import com.example.simpalaapps.presenter.form.FormReportPresenter

class FormReportFragment : Fragment(), FormReportContract.View {

    private lateinit var presenter: FormReportContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_form_report, container, false)

        // Inisialisasi presenter
        presenter = FormReportPresenter(this, AppDatabase.getInstance(requireContext()).reportDao())

        // Tambahkan logika UI dan onClickListener untuk menyimpan laporan
        val submitButton: Button = view.findViewById(R.id.buttonSubmit)
        submitButton.setOnClickListener {
            // Ambil data dari elemen-elemen UI (EditText, Spinner, dll.)
            val report = createReportFromInput()

            // Panggil metode presenter untuk menyimpan laporan
            presenter.submitReport(report)
        }

        return view
    }

    private fun createReportFromInput(): ReportEntity {
        val reportTypeEditText: EditText = view?.findViewById(R.id.editTextReportType)!!
        val reporterNameEditText: EditText = view?.findViewById(R.id.editTextReporterName)!!
        val latitudeEditText: EditText = view?.findViewById(R.id.editTextLatitude)!!
        val longitudeEditText: EditText = view?.findViewById(R.id.editTextLongitude)!!
        val photoEditText: EditText = view?.findViewById(R.id.editTextPhoto)!!
        val reportingDateEditText: EditText = view?.findViewById(R.id.editTextReportingDate)!!
        val reporterEmailEditText: EditText = view?.findViewById(R.id.editTextReporterEmail)!!

        val reportType = reportTypeEditText.text.toString()
        val reporterName = reporterNameEditText.text.toString()
        val latitude = latitudeEditText.text.toString().toDouble()
        val longitude = longitudeEditText.text.toString().toDouble()
        val photo = photoEditText.text.toString()
        val reportingDate = reportingDateEditText.text.toString()
        val reporterEmail = reporterEmailEditText.text.toString()

        return ReportEntity(
            id = 0, // Atau berikan nilai ID yang sesuai dengan kebijakan Anda
            reportType = reportType,
            reporterName = reporterName,
            latitude = latitude,
            longitude = longitude,
            photo = photo,
            reportingDate = reportingDate,
            reporterEmail = reporterEmail
        )
    }
}

