package com.c22ps175.playlab.database.response

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class MessageSupport(
    val text: String? = null,
    val name: String? = null,
    val photoUrl: String? = null,
    val timestamp: Long? = null
)
