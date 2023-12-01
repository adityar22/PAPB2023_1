package com.example.simpalaapps.view.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpalaapps.R
import com.example.simpalaapps.adapter.NewsAdapter
import com.example.simpalaapps.adapter.ReportAdapter
import com.example.simpalaapps.model.AppDatabase
import com.example.simpalaapps.model.Report
import com.example.simpalaapps.model.ReportEntity
import com.example.simpalaapps.model.ReportRepository
import com.example.simpalaapps.model.ReportsResponse
import com.example.simpalaapps.model.news.News
import com.example.simpalaapps.model.news.NewsDao
import com.example.simpalaapps.model.news.NewsEntity
import com.example.simpalaapps.model.news.NewsRepository
import com.example.simpalaapps.model.news.NewsResponse
import com.example.simpalaapps.presenter.DashboardPresenter

import com.example.simpalaapps.presenter.news.NewsContract
import com.example.simpalaapps.presenter.news.NewsPresenter
import com.example.simpalaapps.retrofit.RetrofitInstance
import com.example.simpalaapps.view.report.DetailReportFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment: Fragment(), NewsContract.View, NewsAdapter.OnItemClickListener {
    private lateinit var presenter: NewsContract.Presenter
    private lateinit var repository: NewsRepository
    private lateinit var newsDao: NewsDao
    override val lifecycleScope_: CoroutineScope
        get() = lifecycleScope

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        newsDao = AppDatabase.getInstance(requireContext()).newsDao()
        repository = NewsRepository(newsDao)
        // Inisialisasi presenter
        presenter = NewsPresenter(this, AppDatabase.getInstance(requireContext()).newsDao(), repository, requireContext())
        // Load data
//        if (savedInstanceState == null) {
//            presenter.injectDummyData()
//        }

        lifecycleScope.launch {
            presenter.loadNews()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inject Dummy Data to Presenter
        injectDummyData()
    }

    private suspend fun generateReport(news: List<NewsEntity>) {
        withContext(Dispatchers.IO) {
//            reportDao.insertReport(reports)
            newsDao.insertAll(news)
        }
    }

    // temp function to prevent cast error
    private fun convertResponseNewsToEntity(currentObject: News): NewsEntity {
        val updatedObject = NewsEntity(
            currentObject.newsId,
            currentObject.newsTitle,
            currentObject.newsTag,
            currentObject.newsContent,
            currentObject.photo,
            currentObject.newsDate,
            currentObject.isPremium
        )

        // Perbarui objek pada indeks tertentu di dalam list
        return updatedObject

    }

    private fun injectDummyData() {
        RetrofitInstance.newsApi.getNewsFromApi().enqueue(object: Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if(response.body() != null){
                    val news = arrayListOf<NewsEntity>()
                    for (r in response.body()!!.reports){
                        news.add(convertResponseNewsToEntity(r))
                    }
                    lifecycleScope.launch {
                        generateReport(news)
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("ERROR FETCH", t.message.toString())
            }

        })
    }

    override fun showNews(news: List<NewsEntity>) {
        // Tampilkan data di RecyclerView
        val adapter = NewsAdapter(news)
        adapter.setOnItemClickListener(this)
        val recyclerView: RecyclerView = view?.findViewById(R.id.recyclerView)!!
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    override fun onItemClick(news: NewsEntity) {
        // Handle item click if needed
    }

    override fun onViewDetailClick(news: NewsEntity) {
        showReportDetail(news)
    }

    private fun showReportDetail(news: NewsEntity) {
        val detailNewsFragment = DetailReportFragment.newInstance(news.id!!)
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, detailNewsFragment)
            .addToBackStack(null) // Untuk menambahkan fragment ke dalam back stack
            .commit()
    }

}