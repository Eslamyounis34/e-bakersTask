package com.younis.e_bakerstask.data.remote

import com.younis.e_bakerstask.model.AddToCartResponse
import com.younis.e_bakerstask.model.CartResponse
import com.younis.e_bakerstask.model.UserProfileResponse
import io.reactivex.Observable
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @POST("api/customer/carts")
    fun getCartData(@Header("Authorization") auth: String): Observable<CartResponse>

    @POST("/api/customer/profile")
    fun getProfileData(@Header("Authorization") auth: String): Observable<UserProfileResponse>

    @POST("/api/customer/addToCart")
    fun addToCart(@Query("product_id")product_id:Int, @Query("qty")product_qty:Double,
                  @Header("Authorization")auth: String):Observable<AddToCartResponse>
}
