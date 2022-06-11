package com.c22ps175.playlab.ui.dashboard.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c22ps175.playlab.ui.model.UserPreference
import kotlinx.coroutines.launch

class UserProfileViewModel(private val pref: UserPreference): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is User Profile Activity"
    }
    val text: LiveData<String> = _text

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }
}