package com.younis.newapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.younis.newapp.data.local.room.ArticlesDataBase
import com.younis.newapp.model.Article
import com.younis.newapp.model.NewsResponse
import com.younis.newapp.repositories.ArticleDataSource
import com.younis.newapp.repositories.ArticleDataSourceFactory
import com.younis.newapp.repositories.BreakingNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class BreakingNewsViewModel(application: Application) : AndroidViewModel(application) {

    val articlePagedList: LiveData<PagedList<Article>>
    var articleList: LiveData<List<Article>>
    private val liveDataSource: LiveData<ArticleDataSource>
    private val breakingNewsRepo: BreakingNewsRepository
    private val itemDataSourceFactory = ArticleDataSourceFactory()
    var   checkExist=false

    init {

        val favDao = ArticlesDataBase.getAppDataBase(application)!!.articleDao()!!
        breakingNewsRepo = BreakingNewsRepository(favDao)
        this.articleList = breakingNewsRepo.getAllArticles()

        liveDataSource = itemDataSourceFactory.articlesLiveDataSource
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(ArticleDataSource.PAGE_SIZE)
            .build()

        articlePagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
        Log.e("CheckData", articlePagedList.toString())

    }

    fun insertArticle(article: Article) =viewModelScope.launch {
        breakingNewsRepo.insertArticle(article)
    }


    fun deleteArticle(article: Article)=viewModelScope.launch {
        breakingNewsRepo.deleteArticle(article)

    }


    fun isArticleSaved(id: String):Boolean{
      //  checkExist=false
        viewModelScope.launch {
            checkExist= withContext(Dispatchers.IO){
                breakingNewsRepo.checkExist(id)
            }
        }
        return checkExist
    }



}