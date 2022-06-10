package com.c22ps175.playlab.ui.signup

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.c22ps175.playlab.R
import com.c22ps175.playlab.databinding.ActivitySignupBinding
import com.c22ps175.playlab.ui.login.LoginActivity

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var signupViewModel: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signupViewModel = ViewModelProvider(this)[SignupViewModel::class.java]

        setupAction()

        supportActionBar?.hide()

        binding.masuk.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        validateRegister()
    }

    private fun validateRegister(){
        signupViewModel.isLoading.observe(this) {
            isLoading(it)
            isLoadingIntent(it)
        }

        signupViewModel.apiResponse.observe(this) { mess ->
            Toast.makeText(this, mess, Toast.LENGTH_SHORT).show()
        }

        signupViewModel.isNameEmpty.observe(this) {
            if (it) binding.nameEditTextLayout.error = getString(R.string.requires_name)
        }

        signupViewModel.isEmailEmpty.observe(this) {
            if (it) binding.emailEditText.error = getString(R.string.requires_email)
        }

        signupViewModel.isPasswordEmpty.observe(this) {
            if (it) binding.passwordEditText.error = getString(R.string.requires_password)
        }

        signupViewModel.isPasswordValid.observe(this) {
            if (!it) binding.passwordEditText.error = getString(R.string.password_error) else binding.emailEditText.error =
                null
        }
    }

    private fun setupAction() {
        binding.signupButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            signupViewModel.signUp(this,name,email,password)

        }
    }

    private fun isLoading(value:Boolean){
        if (value){
            binding.signupButton.isEnabled = false
            binding.pgSignup.visibility = View.VISIBLE
        }else{
            binding.signupButton.isEnabled = true
            binding.pgSignup.visibility = View.GONE
        }
    }

    private fun isLoadingIntent(value: Boolean){
        if (value){return}
        else{
            Intent(this, LoginActivity::class.java).let {
                startActivity(it)
            }
        }
    }
}
