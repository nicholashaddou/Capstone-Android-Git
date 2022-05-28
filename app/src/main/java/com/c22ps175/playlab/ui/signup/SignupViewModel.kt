package com.c22ps175.playlab.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c22ps175.playlab.ui.model.UserModel
import com.c22ps175.playlab.ui.model.UserPreference
import kotlinx.coroutines.launch

class SignupViewModel(private val pref: UserPreference) : ViewModel() {
    fun saveUser(user: UserModel) {
        viewModelScope.launch {
            pref.saveUser(user)
        }
    }
}
