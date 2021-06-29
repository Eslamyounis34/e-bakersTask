package com.younis.newapp.data.remote

import com.younis.newapp.model.Constants.Companion.BASE_URL
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetroInstance {
    companion object {

        private val retrofit by lazy {
            val client = OkHttpClient.Builder().build()
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        val api by lazy {
            retrofit.create(NewsApi::class.java)

        }
    }
}