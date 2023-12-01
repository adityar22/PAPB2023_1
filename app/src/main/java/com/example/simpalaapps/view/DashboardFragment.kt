package com.example.simpalaapps.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpalaapps.R
import com.example.simpalaapps.adapter.ReportAdapter
import com.example.simpalaapps.model.AppDatabase
import com.example.simpalaapps.model.Report
import com.example.simpalaapps.model.ReportDao
import com.example.simpalaapps.model.ReportEntity
import com.example.simpalaapps.model.ReportsResponse
import com.example.simpalaapps.presenter.DashboardContract
import com.example.simpalaapps.presenter.DashboardPresenter
import com.example.simpalaapps.model.ReportRepository
import com.example.simpalaapps.retrofit.RetrofitInstance
import com.example.simpalaapps.view.report.DetailReportFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DashboardFragment : Fragment(), DashboardContract.View, ReportAdapter.OnItemClickListener {

    private lateinit var presenter: DashboardPresenter
    private lateinit var repository: ReportRepository
    private lateinit var reportDao: ReportDao
    private lateinit var adapter: ReportAdapter
    private lateinit var searchView: SearchView
    override val lifecycleScope_: CoroutineScope
        get() = lifecycleScope

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        reportDao = AppDatabase.getInstance(requireContext()).reportDao()
        repository = ReportRepository(reportDao)
        presenter = DashboardPresenter(this, AppDatabase.getInstance(requireContext()).reportDao(), repository, requireContext())

        lifecycleScope.launch {
            presenter.loadReports()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView = view.findViewById(R.id.reportSearchView)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                lifecycleScope.launch {
                    presenter.searchReport(newText.orEmpty())
                }
                return true
            }
        })
        // Inject Dummy Data to Presenter
        injectDummyData()
    }

    private suspend fun generateReport(reports: List<ReportEntity>) {
        presenter.insertAllReport(reports)
    }

    private fun convertResponseReportToEntity(currentObject: Report): ReportEntity {
        val byteArray = currentObject.photo.toByteArray()
        val updatedObject = ReportEntity(
            currentObject.reportId,
            currentObject.reportType,
            currentObject.reporterName,
            currentObject.reportDesc ?: "-",
            currentObject.latitude,
            currentObject.longitude,
            byteArray,
            currentObject.reportingDate,
            currentObject.reporterEmail,
            currentObject.isReadOnly
        )

        return updatedObject

    }

    private fun injectDummyData() {
        RetrofitInstance.api.getReportsFromApi().enqueue(object: Callback<ReportsResponse> {
            override fun onResponse(call: Call<ReportsResponse>, response: Response<ReportsResponse>) {
                if(response.body() != null){
                    val newReports = arrayListOf<ReportEntity>()
                    for (r in response.body()!!.reports){
                        newReports.add(convertResponseReportToEntity(r))
                    }
                    lifecycleScope.launch {
                        generateReport(newReports)
                    }
                }
            }

            override fun onFailure(call: Call<ReportsResponse>, t: Throwable) {
                Log.e("ERROR FETCH", t.message.toString())
            }

        })
    }

    override fun showReports(reports: List<ReportEntity>) {
        // Tampilkan data di RecyclerView
        adapter = ReportAdapter(reports)
        adapter.setOnItemClickListener(this)
        val recyclerView: RecyclerView = view?.findViewById(R.id.recyclerView)!!
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    override fun onItemClick(report: ReportEntity) {
        // Implemented soon
    }

    override fun onViewDetailClick(report: ReportEntity) {
        showReportDetail(report)
    }

    private fun showReportDetail(report: ReportEntity) {
        val detailFragment = DetailReportFragment.newInstance(report.id!!)
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, detailFragment)
            .addToBackStack(null)
            .commit()
    }

}