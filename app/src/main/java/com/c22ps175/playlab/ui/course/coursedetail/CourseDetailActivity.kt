package com.c22ps175.playlab.ui.course.coursedetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.c22ps175.playlab.R
import com.c22ps175.playlab.databinding.ActivityCourseDetailBinding
import com.c22ps175.playlab.databinding.ActivityLoginBinding
import com.c22ps175.playlab.ui.course.CourseActivity

class CourseDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCourseDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.buttonMasukCourse.setOnClickListener {
            Intent(this@CourseDetailActivity, CourseActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}