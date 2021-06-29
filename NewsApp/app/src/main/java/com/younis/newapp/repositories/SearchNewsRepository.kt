package com.younis.newapp.repositories

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.younis.newapp.data.remote.RetroInstance
import com.younis.newapp.model.Article
import com.younis.newapp.model.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchNewsRepository(val application: Application) {

    fun getSearchArticles(): MutableLiveData<List<Article>> {

        val articlesList = MutableLiveData<List<Article>>()
        var disposable: Disposable? = null


        disposable = RetroInstance.api.searchNews(Constants.API_KEY, "android", "popularity")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                articlesList.value = it.articles
//                Toast.makeText(context,it.articles.toString(), Toast.LENGTH_LONG).show()
            })
        return articlesList

    }
}