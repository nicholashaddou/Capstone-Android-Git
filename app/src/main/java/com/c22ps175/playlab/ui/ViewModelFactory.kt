package com.c22ps175.playlab.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.c22ps175.playlab.ui.login.LoginViewModel
import com.c22ps175.playlab.ui.signup.SignupViewModel
import com.c22ps175.playlab.ui.model.UserPreference

class ViewModelFactory(private val pref: UserPreference) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(pref) as T
            }
            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
                SignupViewModel() as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}

class MainViewModel(pref: UserPreference) {

}
