package com.younis.e_bakerstask.model

import com.google.gson.annotations.SerializedName

data class Product(
//    val IsProductFavoirte: String,
    @SerializedName("ProductInCart")
    val ProductInCart: Int,
    @SerializedName("ProductInCartQty")
    val ProductInCartQty: Double,
    @SerializedName("ProductInCartTotal")
    val ProductInCartTotal: Double,
    @SerializedName("sale_price")
    val sale_price: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
//    val best_seller: Boolean,
//    val brand: String,
//    val category: String,
//    val colorInCart: String,
//    val colors: List<Any>,
//    val count_solid: Int,
//    val created_by: String,
//    val currency: String,
//    val description: String,
//    val discount: Int,
//    val expire_date_hot_deal: String,
//    val featured: Boolean,
//    val hot_deal: Boolean,
//    val hot_deal_price: Int,

//    val is_new: Boolean,
//    val link_youtube: String,

//    val number_clicks: Int,
//    val number_views: Int,
//    val off_50: Boolean,
//    val on_sale: Boolean,
//    val porduct_sku_code: String,
//    val productImages: List<ProductImage>,
//    val product_code: String,
//    val product_link: String,
//    val product_serial_number: String,
//    val reviews: List<Any>,

//    val short_description: String,
//    val status: String,
//    val stock: Int,
//    val stock_limit_alert: Int,
//    val subcategory: String,
//    val tasteInCart: String,
//    val tastes: List<Any>,
//    val total: Int,
//    val total_number_review: Int,
//    val total_rate: Int,
//    val total_with_currency: String,
//    val trending: Boolean,
//    val unit: String,
//    val unit_value: Int,
//    val updated_by: String
)