package com.example.simpalaapps.view.news

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
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.simpalaapps.R
import com.example.simpalaapps.model.AppDatabase
import com.example.simpalaapps.model.news.NewsEntity
import com.example.simpalaapps.model.news.NewsRepository
import com.example.simpalaapps.presenter.detail.news.DetailNewsContract
import com.example.simpalaapps.presenter.detail.news.DetailNewsPresenter
import com.example.simpalaapps.view.DashboardFragment
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class DetailNewsFragment: Fragment(), DetailNewsContract.View {
    private lateinit var presenter: DetailNewsContract.Presenter
    private var newsId: Long = 0
    private lateinit var news: NewsEntity

    companion object {
        private const val ARG_NEWS_ID = "arg_news_id"
        fun newInstance(newsId: Long): DetailNewsFragment {
            val fragment = DetailNewsFragment()
            val args = Bundle()
            args.putLong(ARG_NEWS_ID, newsId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_news, container, false)

        newsId = arguments?.getLong(ARG_NEWS_ID) ?: 0

        val newsDao = AppDatabase.getInstance(requireContext()).newsDao()
        val repository = NewsRepository(newsDao)

        presenter = DetailNewsPresenter(this, repository)
        lifecycleScope.launch {
            presenter.onViewCreated(newsId)
        }

        return view
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

    private fun redirectToBrowser(url: String) {
        val httpIntent = Intent(Intent.ACTION_VIEW)
        httpIntent.data = Uri.parse(url)

        startActivity(httpIntent)
    }

    override fun showNewsDetails(news: NewsEntity) {
        this.news = news

        val newsTitleTextView: TextView = requireView().findViewById(R.id.newsTitleTextView)
        val photoImageView: ImageView = requireView().findViewById(R.id.photoImageView)
        val newsTagTextView: TextView = requireView().findViewById(R.id.newsTagTextView)
        val newsContentTextView: TextView = requireView().findViewById(R.id.newsContentTextView)
        val newsDateTextView: TextView = requireView().findViewById(R.id.newsDateTextView)
        val buttonSeeOnBrowser: Button = requireView().findViewById(R.id.buttonSeeOnBrowser)

        news?.let {
            newsTitleTextView.text = "${it.newsTitle}"
            newsTagTextView.text = "Tag: ${it.newsTag}"
            newsContentTextView.text = "${it.newsContent}"
            newsDateTextView.text = "${it.newsDate}"

            Picasso.get().load(it.photo).into(photoImageView)

            buttonSeeOnBrowser.setOnClickListener {
                redirectToBrowser(news.url)
            }

        }
    }

}