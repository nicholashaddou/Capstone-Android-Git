package com.c22ps175.playlab.ui.dashboard.profile

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.c22ps175.playlab.R
import com.c22ps175.playlab.data.UserGameLabData
import com.c22ps175.playlab.databinding.ActivityUserProfileBinding
import com.c22ps175.playlab.ui.ViewModelFactory
import com.c22ps175.playlab.ui.model.UserPreference
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

        val dataGameLabUser = intent.getParcelableExtra<UserGameLabData>(EXTRA_USER) as UserGameLabData

        setupViewModel()

        Glide.with(this)
            .load(dataGameLabUser.photo)
            .circleCrop()
            .into(bindingUserProfile.includeHeaderDetail.userDetailAvatar)
        bindingUserProfile.includeHeaderDetail.userDetailFullName.text = dataGameLabUser.name
        bindingUserProfile.includeHeaderDetail.userDetailUsername.text = dataGameLabUser.username

        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionUsernameUser.text = dataGameLabUser.username
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionFullNameUser.text = dataGameLabUser.name
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionIDCourseUser.text = dataGameLabUser.idcourse
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionEmailUser.text = dataGameLabUser.email
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionPhoneUser.text = dataGameLabUser.phone
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionFullAddressUser.text = dataGameLabUser.address
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionProvinceUser.text = dataGameLabUser.province
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionCityUser.text = dataGameLabUser.city
        bindingUserProfile.includeDescriptionDetail.userDetailDescriptionCoursePackageUser.text = dataGameLabUser.packagecourse

        val btnLogout: TextView = findViewById(R.id.user_logout)
        val btnSupport: Button = findViewById(R.id.fab_support)

        btnLogout.setOnClickListener {
            userProfileViewModel.logout()
            Toast.makeText(this, resources.getString(R.string.user_logout_detail), Toast.LENGTH_SHORT).show()
        }

        btnSupport.setOnClickListener {
            val fab: View = findViewById(R.id.fab_support)
            fab.setOnClickListener { view ->
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
            }
            Toast.makeText(this, resources.getString(R.string.fab_support), Toast.LENGTH_SHORT).show()
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