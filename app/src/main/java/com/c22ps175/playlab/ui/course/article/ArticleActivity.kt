package com.c22ps175.playlab.ui.course.article

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.c22ps175.playlab.R
import com.c22ps175.playlab.databinding.ActivityArticleBinding
import com.c22ps175.playlab.ui.dashboard.DashboardActivity

class ArticleActivity : AppCompatActivity() {

    private lateinit var binding : ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonArticleBack.setOnClickListener {
            val intentToDashboard = Intent(this, DashboardActivity::class.java)
            startActivity(intentToDashboard)
        }
    }
}