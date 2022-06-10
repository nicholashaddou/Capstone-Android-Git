package com.c22ps175.playlab.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val userId: String,
    val name: String,
    val token : String,
    val isLogin: Boolean
): Parcelable
