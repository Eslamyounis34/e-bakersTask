package com.younis.e_bakerstask.model

data class UserProfileResponse(
    val data: User,
    val message: String,
    val success: Boolean
)