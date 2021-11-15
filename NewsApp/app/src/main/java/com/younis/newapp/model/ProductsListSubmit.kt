package com.younis.newapp.model

data class ProductsListSubmit(
    val CUSTOMER_ID: String,
    val MATERIAL_QTY: List<MATERIALQTY>,
    val MATERIAL_SERIALS: List<MATERIALSERIALS>,
    val TOKEN: String
)