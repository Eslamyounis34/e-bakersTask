package com.younis.newapp.data.remote

import com.younis.newapp.model.NewsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/v2/top-headlines")
    fun getBreakingNews(
        @Query("country") country: String ,
        @Query("category") category: String ,
        @Query("apiKey") apiKey: String
    ): Observable<NewsResponse>

    @GET("/v2/everything")
    fun searchNews(
        @Query("apiKey")apiKey: String,
        @Query("q")q:String,
        @Query("sortBy")sortBy:String

    ):Observable<NewsResponse>
}