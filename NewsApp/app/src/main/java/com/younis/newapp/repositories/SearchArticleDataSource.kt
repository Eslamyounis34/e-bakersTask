package com.younis.newapp.repositories

import android.app.Application
import androidx.paging.PageKeyedDataSource
import com.younis.newapp.data.remote.RetroInstance
import com.younis.newapp.model.Article
import com.younis.newapp.model.Constants
import com.younis.newapp.model.Constants.Companion.API_KEY
import com.younis.newapp.model.NewsResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SearchArticleDataSource(val searchInput: String) : PageKeyedDataSource<Int, Article>() {

    lateinit var application: Application
    var disposable: Disposable? = null
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Article>
    ) {
        disposable =
            RetroInstance.api.searchNewsByPage(API_KEY, searchInput, "popularity", FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableSingleObserver<NewsResponse>() {
                    override fun onSuccess(t: NewsResponse) {
                        val responseItems = t.articles
                        responseItems.let {
                            callback.onResult(responseItems, null, FIRST_PAGE + 1)
                        }
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        disposable = RetroInstance.api.searchNewsByPage(
            API_KEY, searchInput,
            "popularity", params.key
        )
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<NewsResponse>() {
                override fun onSuccess(t: NewsResponse) {
                    val responseItems = t.articles
                    val key = if (params.key > 1) params.key - 1 else 0
                    responseItems.let {
                        callback.onResult(responseItems, key)
                    }
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        disposable = RetroInstance.api.searchNewsByPage(
            API_KEY, searchInput,
            "popularity", params.key
        )
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<NewsResponse>() {
                override fun onSuccess(t: NewsResponse) {
                    val responseItems = t.articles
                    val key = params.key + 1
                    responseItems.let {
                        callback.onResult(responseItems, key)
                    }
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

            })

    }

    companion object {
        const val PAGE_SIZE = 20
        const val FIRST_PAGE = 1

    }
}