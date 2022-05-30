package com.c22ps175.playlab.database.response

data class LoginResult(
    val userId: String,
    val name: String,
    val token: String
)
