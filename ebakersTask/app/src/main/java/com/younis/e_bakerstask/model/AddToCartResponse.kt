package com.younis.e_bakerstask.model

import com.google.gson.annotations.SerializedName

data class AddToCartResponse(
//    val data: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)