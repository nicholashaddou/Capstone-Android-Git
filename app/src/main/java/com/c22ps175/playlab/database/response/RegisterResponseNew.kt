package com.c22ps175.playlab.database.response

data class RegisterResponseNew(
    val idToken: String,
    val email: String,
    val refreshToken : String,
    val expiresIn: String,
    val localId : String
)
