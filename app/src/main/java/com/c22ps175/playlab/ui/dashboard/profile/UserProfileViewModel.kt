package com.c22ps175.playlab.ui.dashboard.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserProfileViewModel: ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is User Profile Activity"
    }
    val text: LiveData<String> = _text
}