package com.example.simpalaapps.view.news

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpalaapps.R
import com.example.simpalaapps.adapter.NewsAdapter
import com.example.simpalaapps.model.AppDatabase
import com.example.simpalaapps.model.news.News
import com.example.simpalaapps.model.news.NewsDao
import com.example.simpalaapps.model.news.NewsEntity
import com.example.simpalaapps.model.news.NewsRepository
import com.example.simpalaapps.model.news.NewsResponse
import com.example.simpalaapps.presenter.news.NewsContract
import com.example.simpalaapps.presenter.news.NewsPresenter
import com.example.simpalaapps.retrofit.RetrofitInstance
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
    private lateinit var adapter: NewsAdapter
    private lateinit var recyclerView: RecyclerView

    override val lifecycleScope_: CoroutineScope
        get() = lifecycleScope

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_news, container, false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "news_channel_id",
                "News Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager =
                requireActivity().getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        newsDao = AppDatabase.getInstance(requireContext()).newsDao()
        repository = NewsRepository(newsDao)
        // Inisialisasi presenter
        presenter = NewsPresenter(this, AppDatabase.getInstance(requireContext()).newsDao(), repository, requireContext())

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

    private suspend fun generateNews(news: List<NewsEntity>) {
        withContext(Dispatchers.IO) {
            newsDao.insertAll(news)
        }
    }

    // temp function to prevent cast error
    private fun convertResponseNewsToEntity(currentObject: News): NewsEntity {
        val updatedObject = NewsEntity(
            currentObject.newsId,
            currentObject.newsTitle,
            currentObject.newsExcerpt,
            currentObject.newsTag,
            currentObject.newsContent,
            currentObject.photo,
            currentObject.newsDate,
            currentObject.url,
            currentObject.isPremium
        )

        // Perbarui objek pada indeks tertentu di dalam list
        return updatedObject

    }

    private fun injectDummyData() {
        RetrofitInstance.api.getNewsFromApi().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d("NEWS DATA", "${response.body()!!.news}")
                    val news = arrayListOf<NewsEntity>()
                    for (n in response.body()!!.news) {
                        news.add(convertResponseNewsToEntity(n))
                    }
                    lifecycleScope.launch {
                        generateNews(news)

                        // Show notification when news is successfully loaded
                        showNewsLoadedNotification()
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("ERROR FETCH", t.message.toString())
            }
        })
    }

    private fun showNewsLoadedNotification() {
        val notificationManager =
            requireActivity().getSystemService(NotificationManager::class.java)

        val notification = NotificationCompat.Builder(requireContext(), "news_channel_id")
            .setSmallIcon(R.drawable.ic_notifications_black_24dp)
            .setContentTitle("News Loaded")
            .setContentText("News data has been successfully loaded.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(1, notification)
    }


    override fun showNews(news: List<NewsEntity>) {
        // Tampilkan data di RecyclerView
        adapter = NewsAdapter(news)
        adapter.setOnItemClickListener(this)
        recyclerView = view?.findViewById(R.id.recyclerView)!!
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    override fun onItemClick(news: NewsEntity) {
        // Handle item click if needed
    }

    override fun onViewDetailClick(news: NewsEntity) {
        showNewsDetail(news)
    }

    private fun showNewsDetail(news: NewsEntity) {
        val detailNewsFragment = DetailNewsFragment.newInstance(news.id!!)
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, detailNewsFragment)
            .addToBackStack(null) // Untuk menambahkan fragment ke dalam back stack
            .commit()
    }

}