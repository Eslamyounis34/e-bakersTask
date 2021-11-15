package com.younis.newapp.repositories

import androidx.lifecycle.MutableLiveData
import com.younis.newapp.model.Article

class SearchArticleDataSourceFactory : androidx.paging.DataSource.Factory<Int, Article>() {
    val articlesLiveDataSource = MutableLiveData<SearchArticleDataSource>()
   lateinit var searchInput: String

    override fun create(): androidx.paging.DataSource<Int, Article> {

        val searchArticlesDataSource = SearchArticleDataSource(searchInput)
        articlesLiveDataSource.postValue(searchArticlesDataSource)
        return searchArticlesDataSource
    }

}