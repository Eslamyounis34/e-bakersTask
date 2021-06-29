package com.younis.newapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.younis.newapp.model.Article
import com.younis.newapp.repositories.BreakingNewsRepository

class SerachNewsViewModel(application: Application) : AndroidViewModel(application) {


    var articleList: LiveData<List<Article>>
    val breakingNewsRepo = BreakingNewsRepository(application)

    init {
        this.articleList = breakingNewsRepo.getSearchArticles()
    }

    fun getSearchedArticles(): LiveData<List<Article>> {
        return articleList
    }
}