package com.c22ps175.playlab.ui.database.response

data class LoginResponse(
    val loginResult: LoginResult,
    val error: Boolean,
    val message: String
)
