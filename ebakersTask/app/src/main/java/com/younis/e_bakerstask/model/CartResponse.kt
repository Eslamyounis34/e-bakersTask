package com.younis.e_bakerstask.model

import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("data")
    val data: ProductList,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)