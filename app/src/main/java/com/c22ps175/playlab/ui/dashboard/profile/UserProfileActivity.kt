package com.c22ps175.playlab.ui.dashboard.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.c22ps175.playlab.R
import com.c22ps175.playlab.data.UserGameLabData
import com.c22ps175.playlab.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {

    private lateinit var bindingUserProfile: ActivityUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingUserProfile = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(bindingUserProfile.root)

        supportActionBar?.title = resources.getString(R.string.user_detail_profile)

        val dataGameLabUser = intent.getParcelableExtra<UserGameLabData>(EXTRA_USER) as UserGameLabData

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
            Toast.makeText(this, resources.getString(R.string.user_logout_detail), Toast.LENGTH_SHORT).show()
        }

        btnSupport.setOnClickListener {
            Toast.makeText(this, resources.getString(R.string.fab_support), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}