package com.c22ps175.playlab.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserGameLabData (
    var photo: String,
    var name: String,
    var username: String,
    var idcourse: String,
    var email: String,
    var phone: String,
    var address: String,
    var province: String,
    var city: String,
    var packagecourse: String,
): Parcelable
