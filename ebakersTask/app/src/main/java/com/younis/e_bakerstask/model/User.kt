package com.younis.e_bakerstask.model

data class User(
    val city: String,
    val created_at: String,
    val customer_address: String,
    val customer_appartment_number: String,
    val customer_comments_extra: Any,
    val customer_floor_number: String,
    val customer_home_number: String,
    val customer_postal_code: Any,
    val customer_region: Any,
    val customer_street: String,
    val email: String,
    val firebaseToken: Any,
    val full_name: String,
    val gender: String,
    val gift_id: Any,
    val image: String,
    val is_gift_used: Any,
    val phone: String,
    val promocode: String,
    val state: Any,
    val status: String,
    val total_wallet: String
)