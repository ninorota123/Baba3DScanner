// VideoListActivity.kt
package com.bestgame.baba3dscanner

import VideoListAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class VideoListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var folderListAdapter: VideoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_list)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val folderPath = intent.getStringExtra("folderPath")
        if (folderPath != null) {
            loadVideos(File(folderPath))
        }
    }

    private fun loadVideos(folder: File) {
        val videoFiles = folder.listFiles { _, name -> name.endsWith(".mp4", true) }?.toList() ?: listOf()

        folderListAdapter = VideoListAdapter(this, videoFiles) { videoFile ->
            openVideoPlayerActivity(videoFile)
        }
        recyclerView.adapter = folderListAdapter
    }


    private fun openVideoPlayerActivity(video: File) {
        // Starte die VideoPlayerActivity
        val intent = Intent(this, VideoPlayerActivity::class.java)
        intent.putExtra("videoFilePath", video.absolutePath) // Änderung hier
        startActivity(intent)
    }
}
