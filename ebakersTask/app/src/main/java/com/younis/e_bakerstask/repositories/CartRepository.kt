package com.younis.e_bakerstask.repositories

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.younis.e_bakerstask.model.Product
import com.younis.e_bakerstask.data.remote.Api
import com.younis.e_bakerstask.model.AddToCartResponse
import com.younis.e_bakerstask.model.CartResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CartRepository(val application: Application) {

    val cartDataList = MutableLiveData<List<Product>>()
    val addtoCArtResponse=MutableLiveData<AddToCartResponse>()

    val token="eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjI4MTIwZjI2YzI3OTdhYWIxM2E0NzRjMzUyMWM5M2IxNGZiNmQwZDRjYWQ2MmQ3NjU4MDg2NWYwYmMxYzE4MTViZGFmNDdlMGRlZTEzNWU1In0.eyJhdWQiOiIxIiwianRpIjoiMjgxMjBmMjZjMjc5N2FhYjEzYTQ3NGMzNTIxYzkzYjE0ZmI2ZDBkNGNhZDYyZDc2NTgwODY1ZjBiYzFjMTgxNWJkYWY0N2UwZGVlMTM1ZTUiLCJpYXQiOjE2MTY0MTIxMjEsIm5iZiI6MTYxNjQxMjEyMSwiZXhwIjoxNjQ3OTQ4MTIxLCJzdWIiOiIxNjI0Iiwic2NvcGVzIjpbXX0.jtOkQAoI3r7338BVi-b-EKuVUVTU-w5cdwidC9EEbldlKRL1KCCKXN3zzYtcjAFNzbJVz55fNAGMRZcSpz4wCQ7JmaOWs2yR_SPczKMw-KMknMOA5V2EQibC5f591jkr4QHw80Pa_Ht6Yh3jempFRkRZYdmEUR2J0R7J3FQ6zw1gUY9g_NbQRfs_UH8-n9TY2Tpx7It1TjhNkgfa9KduC5AcmHBAs_aLY9UygkdleegtC4lGfl9TRe8GQ4C_1UUlZL2oUegJaeqZdKQx3JKGwhotej5UbOqZRE_sZtKd6YHua2BOIaGa7wn65IQIPbg8wMFraEtA-EA9F7Q5xoB9hWMgv7EjY3GjVuwsVHAqqxviJIx_ENVxhug8toVSPHwaiVfMIi6i9lvzsDLwkZuyEuFcm3X-xGo0fBvc9ntwj_mcNO5kIvJU5YHPRonr45MOcFpr1VB3G_CGIKx71RMJdFWDMwyHAV86MXkb3Rn6gX9vjVV7hEPjVL3yPRt-jI0wGhaaBFrjYJBr6KjmtolrDy8qi3oS0r0cmeO_DypfpFFBlvH0PqSIKn_fMOfSLtziE4M42KWvX9I8Ok6uX3T_QiluEhkK8qYbUtQMfMwh_b5kSorMy8N61CGHb6RbZ3z-HVrMTuVs85X1-Co8BkH2d_DC8RegLsp9hY9RvMKWKX4"

    var disposable: Disposable? = null

    fun getCartData():MutableLiveData<List<Product>>
    {
        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client : OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()
        val retrofit =
            Retrofit.Builder().baseUrl("https://jazarweb.com/").addConverterFactory(
                GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        val service=retrofit.create(Api::class.java)

        disposable=service.getCartData("Bearer "+token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                cartDataList.value=it.data.data

            })
        return cartDataList

    }
    fun getCaraAddData(product_id:Int,product_qty:Double):MutableLiveData<AddToCartResponse>{
        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client : OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()
        val retrofit =
            Retrofit.Builder().baseUrl("https://jazarweb.com/").addConverterFactory(
                GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        val service=retrofit.create(Api::class.java)

        disposable=service.addToCart(product_id,product_qty,"Bearer "+token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                addtoCArtResponse.value=it
            })
        return addtoCArtResponse
    }
}