package com.c22ps175.playlab.ui.signup

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c22ps175.playlab.api.ApiConfig
import com.c22ps175.playlab.database.response.ResponseSementara
import com.c22ps175.playlab.ui.model.UserModel
import com.c22ps175.playlab.ui.model.UserPreference
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel(private val pref: UserPreference) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _isNameEmpty = MutableLiveData<Boolean>()
    val isNameEmpty : LiveData<Boolean> = _isNameEmpty

    private val _isEmailValid = MutableLiveData<Boolean>()
    val isEmailValid : LiveData<Boolean> = _isEmailValid

    private val _isEmailEmpty = MutableLiveData<Boolean>()
    val isEmailEmpty : LiveData<Boolean> = _isEmailEmpty

    private val _isPasswordEmpty = MutableLiveData<Boolean>()
    val isPasswordEmpty : LiveData<Boolean> = _isPasswordEmpty

    private val _isPasswordValid = MutableLiveData<Boolean>()
    val isPasswordValid : LiveData<Boolean> = _isPasswordValid

    private val _apiResponse = MutableLiveData<String>()
    val apiResponse : LiveData<String> = _apiResponse

    private fun passwordValidation(pass: String):Boolean{
        return pass.length >= 6
    }

    private fun emailValidation(email: String):Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun formNotEmpty(name:String, password:String,email:String):Boolean{
        var isNotEmpty = true

        when {
            name.isEmpty() -> {
                _isNameEmpty.value = true
                isNotEmpty = false
            }
            password.isEmpty() -> {
                _isPasswordEmpty.value = true
                isNotEmpty = false
            }
            email.isEmpty() -> {
                _isEmailEmpty.value = true
                isNotEmpty = false
            }
        }
        return isNotEmpty
    }

    private fun formValidation(name: String, pass: String, email: String):Boolean{
        var isValid = true

        if (formNotEmpty(name,pass,email)){
            if (!emailValidation(email)){
                _isEmailValid.value = false
                isValid = false
            }else if(!passwordValidation(pass)){
                _isPasswordValid.value = false
                isValid = false
            }
        }
        else isValid = false
        return isValid
    }

    fun signUp(context: Context, name: String, email: String, pass: String){
        if (formValidation(name,pass,email)){
            val client = ApiConfig.getApiService().postRegister(name,email,pass)
            _isLoading.value = true
            client.enqueue(object : Callback<ResponseSementara> {
                override fun onResponse(
                    call: Call<ResponseSementara>,
                    response: Response<ResponseSementara>
                ) {

                    val responseBody = response.body()
                    if (response.isSuccessful && responseBody != null){
                        val error = responseBody.error
                        if (!error){
                            _isLoading.value = false
                            _apiResponse.value = responseBody.message
                        }else{
                            _isLoading.value = true
                            _apiResponse.value = "Failed to create user"
                        }
                    }else{
                        _isLoading.value = false
                        _apiResponse.value = "Successfully created user"
                    }
                }

                override fun onFailure(call: Call<ResponseSementara>, t: Throwable) {
                    _isLoading.value = true
                    _apiResponse.value = t.message
                }
            })
        }
    }

    fun saveUser(user: UserModel) {
        viewModelScope.launch {
            pref.saveUser(user)
        }
    }
}

private operator fun String.not(): Boolean {
    return false
}

