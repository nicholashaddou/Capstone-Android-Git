package com.c22ps175.playlab.ui.dashboard.profile

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.c22ps175.playlab.R
import com.c22ps175.playlab.data.UserGameLabData
import com.c22ps175.playlab.databinding.ActivityUserProfileBinding
import com.c22ps175.playlab.ui.ViewModelFactory
import com.c22ps175.playlab.ui.main.MainActivity
import com.c22ps175.playlab.ui.model.UserPreference
import com.c22ps175.playlab.ui.support.SupportAgentActivity
import com.c22ps175.playlab.ui.tesml.MLActivity
import com.c22ps175.playlab.ui.welcome.WelcomeActivity
import com.google.android.material.snackbar.Snackbar

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class UserProfileActivity : AppCompatActivity() {

    private lateinit var bindingUserProfile: ActivityUserProfileBinding
    private lateinit var userProfileViewModel: UserProfileViewModel

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingUserProfile = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(bindingUserProfile.root)

        supportActionBar?.title = resources.getString(R.string.user_detail_profile)

        setupViewModel()

        // Mematikan dahulu fungsi loading
        loadingOff()

        /*
        Untuk penerapan pada data API

        val dataGameLabUser = intent.getParcelableExtra<UserGameLabData>(EXTRA_USER)// as UserGameLabData

        Glide.with(this)
            .load(dataGameLabUser?.photo)
            .circleCrop()
            .into(bindingUserProfile.includeHeaderDetail.userDetailAvatar)
        bindingUserProfile.includeHeaderDetail.userDetailFullName.text = dataGameLabUser?.name
        bindingUserProfile.includeHeaderDetail.userDetailUsername.text = dataGameLabUser?.username

        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionUsernameUser.text = dataGameLabUser?.username
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionFullNameUser.text = dataGameLabUser?.name
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionIDCourseUser.text = dataGameLabUser?.idcourse
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionEmailUser.text = dataGameLabUser?.email
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionPhoneUser.text = dataGameLabUser?.phone
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionFullAddressUser.text = dataGameLabUser?.address
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionProvinceUser.text = dataGameLabUser?.province
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionCityUser.text = dataGameLabUser?.city
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionCoursePackageUser.text = dataGameLabUser?.packagecourse

         */

        // Data Dummy sementara

        Glide.with(this)
            .load(resources.getString(R.string.dummy_data_avatar))
            .circleCrop()
            .into(bindingUserProfile.includeHeaderDetail.userDetailAvatar)
        bindingUserProfile.includeHeaderDetail.userDetailFullName.text = resources.getString(R.string.dummy_data_name)
        bindingUserProfile.includeHeaderDetail.userDetailUsername.text = resources.getString(R.string.dummy_data_username)

        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionUsernameUser.text = resources.getString(R.string.dummy_data_username)
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionFullNameUser.text = resources.getString(R.string.dummy_data_name)
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionIDCourseUser.text = resources.getString(R.string.dummy_data_idcourse)
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionEmailUser.text = resources.getString(R.string.dummy_data_email)
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionPhoneUser.text = resources.getString(R.string.dummy_data_phone)
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionFullAddressUser.text = resources.getString(R.string.dummy_data_address)
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionProvinceUser.text = resources.getString(R.string.dummy_data_province)
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionCityUser.text = resources.getString(R.string.dummy_data_city)
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionCoursePackageUser.text = resources.getString(R.string.dummy_data_packagecourse)

        val btnLogout: TextView = findViewById(R.id.user_logout)

        btnLogout.setOnClickListener {
            userProfileViewModel.logout()
            Toast.makeText(this, resources.getString(R.string.user_logout_detail), Toast.LENGTH_SHORT).show()
            val intentLogout = Intent(this@UserProfileActivity, MainActivity::class.java)
            startActivity(intentLogout)
        }



        val btnSupport: FloatingActionButton = findViewById(R.id.fab_support)

        btnSupport.setOnClickListener {
            val fab: View = findViewById(R.id.fab_support)
            fab.setOnClickListener { view ->
                val moveIntent = Intent(this@UserProfileActivity, SupportAgentActivity::class.java)
                startActivity(moveIntent)

                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
            }
            Toast.makeText(this, resources.getString(R.string.fab_support), Toast.LENGTH_SHORT).show()
        }


    }

    // Mematikan loading dahulu sementara sebagai dummy
    private fun loadingOff() {
        bindingUserProfile.apply {
            progressBarDetail.visibility = View.GONE
        }
    }

    private fun setupViewModel(){
        userProfileViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore), this)
        )[UserProfileViewModel::class.java]
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}
