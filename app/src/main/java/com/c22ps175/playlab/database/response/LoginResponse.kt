package com.c22ps175.playlab.database.response

data class LoginResponse(
    val loginResult: LoginResult,
    val error: Boolean,
    val message: String
)
