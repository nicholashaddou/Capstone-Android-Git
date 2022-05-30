package com.c22ps175.playlab.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.bumptech.glide.Glide
import com.c22ps175.playlab.R
import com.c22ps175.playlab.databinding.ActivityMainBinding
import com.c22ps175.playlab.ui.login.LoginActivity
import com.c22ps175.playlab.ui.signup.SignupActivity

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity(), View.OnClickListener  {
    private lateinit var binding: ActivityMainBinding
    private var imageWelcomeSpotlight = R.drawable.ui_ux_welcome_kecil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            Glide.with(this@MainActivity)
                .load(imageWelcomeSpotlight)
                .circleCrop()
                .into(imageSpotlight)
        }

        val buttonSignUp: Button = findViewById(R.id.button_sign_up)
        buttonSignUp.setOnClickListener(this)

        val buttonLogIn: Button = findViewById(R.id.button_log_in)
        buttonLogIn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_sign_up -> {
                val signUpIntent = Intent(this@MainActivity, SignupActivity::class.java)
                startActivity(signUpIntent)
            }
            R.id.button_log_in -> {
                val logInIntent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(logInIntent)
            }
        }
    }
}
