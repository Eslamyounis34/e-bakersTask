package com.younis.newapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.younis.newapp.R
import com.younis.newapp.model.Article

class NewsAdapter(private val articlesList: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var articleImage: ImageView
        var articleTitle: TextView

        init {
            articleImage = itemView.findViewById(R.id.newsimage)
            articleTitle = itemView.findViewById(R.id.newstitle)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v =
            LayoutInflater.from(parent.context).inflate(R.layout.newsrecycleritemrow, parent, false)

        return NewsAdapter.ViewHolder(v)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.articleTitle.setText(articlesList[position].title)
        Picasso.get().load(articlesList[position].urlToImage).into(holder.articleImage)
    }

    override fun getItemCount() = articlesList.size
}