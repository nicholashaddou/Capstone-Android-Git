package com.c22ps175.playlab.ui.course

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.c22ps175.playlab.databinding.ActivityCourseListBinding
import com.c22ps175.playlab.ui.adapters.CourseListAdapter

class CourseListActivity : AppCompatActivity() {

    private lateinit var courseAdapter: CourseListAdapter
    private lateinit var binding: ActivityCourseListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        courseAdapter = CourseListAdapter()
    }

}