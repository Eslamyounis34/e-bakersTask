package com.younis.newapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.younis.newapp.model.Article
import com.younis.newapp.repositories.BreakingNewsRepository

class BreakingNewsViewModel(application: Application) : AndroidViewModel(application) {
    var articleList: LiveData<List<Article>>
    val breakingNewsRepo = BreakingNewsRepository(application)

    init {
        this.articleList = breakingNewsRepo.getAllArticles()
    }

    fun getAllArticles(): LiveData<List<Article>> {
        return articleList
    }
}