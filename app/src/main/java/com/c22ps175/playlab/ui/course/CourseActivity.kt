package com.c22ps175.playlab.ui.course

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.c22ps175.playlab.databinding.ActivityCourseBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class CourseActivity : AppCompatActivity() {
    companion object {
        const val URL_VIDEO = "https://github.com/nicholashaddou/Capstone-Android-Git/releases/download/release/AdvancedCSharpLow.mp4"
        const val URL_AUDIO = "https://github.com/nicholashaddou/Capstone-Android-Git/releases/download/release/AdvancedCSharpLow.mp3"
    }

    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityCourseBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

        val player = ExoPlayer.Builder(this).build()
        viewBinding.videoView.player = player

        supportActionBar?.hide()

        val mediaItem = MediaItem.fromUri(URL_VIDEO)
        val anotherMediaItem = MediaItem.fromUri(URL_AUDIO)
        player.setMediaItem(mediaItem)
        player.addMediaItem(anotherMediaItem)
        player.prepare()
    }
}