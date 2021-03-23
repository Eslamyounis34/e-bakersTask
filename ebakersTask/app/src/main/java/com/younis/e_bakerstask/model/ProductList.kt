package com.younis.e_bakerstask.model

import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("data")
    val data: List<Product>
)