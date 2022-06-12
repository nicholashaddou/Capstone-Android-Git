package com.c22ps175.playlab.database.response


data class UserAuthResponse(
    val idToken : String,
    val refreshToken : String,
    val expiresIn: String
)
