package com.younis.newapp.repositories

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.younis.newapp.data.local.room.ArticlesDao
import com.younis.newapp.data.remote.NewsApi
import com.younis.newapp.data.remote.RetroInstance
import com.younis.newapp.model.Article
import com.younis.newapp.model.Constants.Companion.API_KEY
import com.younis.newapp.model.Constants.Companion.BASE_URL
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BreakingNewsRepository(val db: ArticlesDao) {

    val articlesList = MutableLiveData<List<Article>>()
    var disposable: Disposable? = null

    fun getAllArticles(): MutableLiveData<List<Article>> {
        disposable = RetroInstance.api.getBreakingNews("us", "business", API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                articlesList.value = it.articles
            }

        return articlesList
    }

    fun getSearchArticles(query: String): MutableLiveData<List<Article>> {
        disposable = RetroInstance.api.searchNews(API_KEY, query, "popularity")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                articlesList.value = it.articles
            }
        return articlesList
    }

   suspend fun insertArticle(article: Article) {
        db.insertArticle(article)
    }


   suspend fun deleteArticle(article: Article){
        db.deleteArticle(article)
    }

   suspend fun checkExist(id :String): Boolean {
        val exists=db.isExisted(id)
        return exists
    }
}