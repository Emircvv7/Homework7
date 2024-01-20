package com.example.homework_1.video

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class YouTubePlayerActivity : AppCompatActivity() {

    private lateinit var youTubePlayerView: YouTubePlayerView
    private var currentVideoTime: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        youTubePlayerView = YouTubePlayerView(this)
        setContentView(youTubePlayerView)

        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = intent.getStringExtra("videoId")
                if (videoId != null) {
                    youTubePlayer.loadVideo(videoId, currentVideoTime)
                }
            }

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                currentVideoTime = second
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putFloat("currentVideoTime", currentVideoTime)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentVideoTime = savedInstanceState.getFloat("currentVideoTime")
    }

    override fun onDestroy() {
        super.onDestroy()
        youTubePlayerView.release()
    }
}
