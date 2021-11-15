package com.younis.newapp.ui.adapters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.younis.newapp.R
import com.younis.newapp.model.Article
import com.younis.newapp.model.OnArticleListner


class NewsAdapter() :
    PagedListAdapter<Article, NewsAdapter.ViewHolder>(
        USER_COMPARATOR
    ) {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var articleImage: ImageView
        var articleTitle: TextView
        init {
            articleImage=itemView.findViewById(R.id.newsimage)
            articleTitle=itemView.findViewById(R.id.newstitle)
        }
    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean =
                newItem == oldItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v =
            LayoutInflater.from(parent.context).inflate(R.layout.newsrecycleritemrow, parent, false)
        return NewsAdapter.ViewHolder(v)
    }

    private var onItemClickListener: OnArticleListner? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val article = getItem(position)

        holder.itemView.apply {
            holder.articleTitle.text = article?.title
            Picasso.get().load(article?.urlToImage).into(holder.articleImage)

            setOnClickListener {
                onItemClickListener?.onclick(getItem(position)!!)
            }
        }
    }

    fun setOnItemClickListener(listener: OnArticleListner) {
        onItemClickListener = listener
    }


}