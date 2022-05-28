package com.c22ps175.playlab.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    var name: String,
    var email: String,
    var password: String,
    var isLogin: Boolean
): Parcelable
