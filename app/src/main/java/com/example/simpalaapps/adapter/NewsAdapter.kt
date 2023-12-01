package com.example.simpalaapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simpalaapps.R
import com.example.simpalaapps.model.news.NewsEntity
import com.squareup.picasso.Picasso

class NewsAdapter(private val news: List<NewsEntity>):
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(news: NewsEntity)
        fun onViewDetailClick(news: NewsEntity)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val new = news[position]
        holder.bind(new)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textNewsTitle: TextView = itemView.findViewById(R.id.textNewsTitle)
        private val textNewsTag: TextView = itemView.findViewById(R.id.textNewsTag)
        private val textNewsExcerpt: TextView = itemView.findViewById(R.id.textNewsExcerpt)
        private val textNewsDate: TextView = itemView.findViewById(R.id.textNewsDate)
        private val buttonViewDetail: Button = itemView.findViewById(R.id.buttonViewDetail)

        private val imageView : ImageView = itemView.findViewById(R.id.newsImage)

        fun bind(news: NewsEntity) {
            textNewsTitle.text = news.newsTitle
            textNewsTag.text = "Tag: ${news.newsTag}"
            textNewsDate.text = news.newsDate
            textNewsExcerpt.text = news.newsExcerpt
            Picasso.get().load(news.photo).into(imageView)

            // Set onClickListener for the item
            itemView.setOnClickListener {
                listener?.onItemClick(news)
            }

            // Set onClickListener for the "View Detail" button
            buttonViewDetail.setOnClickListener {
                listener?.onViewDetailClick(news)
            }
        }
    }

}