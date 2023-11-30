package com.example.simpalaapps.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpalaapps.R
import com.example.simpalaapps.adapter.ReportAdapter
import com.example.simpalaapps.model.AppDatabase
import com.example.simpalaapps.model.ReportEntity
import com.example.simpalaapps.presenter.DashboardContract
import com.example.simpalaapps.presenter.DashboardPresenter
import com.example.simpalaapps.presenter.detail.ReportRepository
import com.example.simpalaapps.view.report.DetailReportFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class DashboardFragment : Fragment(), DashboardContract.View {

    private lateinit var presenter: DashboardContract.Presenter
    override val lifecycleScope_: CoroutineScope
        get() = lifecycleScope

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val reportDao = AppDatabase.getInstance(requireContext()).reportDao()
        val repository = ReportRepository(reportDao)
        // Inisialisasi presenter
        presenter = DashboardPresenter(this, AppDatabase.getInstance(requireContext()).reportDao(), repository, requireContext())
        // Load data
        if (savedInstanceState == null) {
            presenter.injectDummyData()
        }

        lifecycleScope.launch {
            presenter.loadReports()
        }
        return view
    }

    override fun showReports(reports: List<ReportEntity>) {
        // Tampilkan data di RecyclerView
        val recyclerView: RecyclerView = view?.findViewById(R.id.recyclerView)!!
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ReportAdapter(reports)
    }

    private fun showReportDetail(report: ReportEntity) {
        val detailFragment = DetailReportFragment.newInstance(report.id)
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, detailFragment)
            .addToBackStack(null) // Untuk menambahkan fragment ke dalam back stack
            .commit()
    }
}