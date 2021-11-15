package com.younis.newapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.younis.newapp.data.local.room.ArticlesDataBase
import com.younis.newapp.model.Article
import com.younis.newapp.repositories.*

class SerachNewsViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var articlePagedList: LiveData<PagedList<Article>>
    private val itemDataSourceFactory = SearchArticleDataSourceFactory()

    fun setListId(searchInput: String) {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .build()

        articlePagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
        itemDataSourceFactory.searchInput = searchInput
    }
    }