package com.bestgame.baba3dscanner

import VideoListAdapter
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class Entwuerfe_Activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var videoListAdapter: VideoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entwuerfe)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadVideos()
    }

    private fun loadVideos() {
        val rootFolder = File("${getExternalFilesDir(Environment.DIRECTORY_MOVIES)}/Baba3DScanner/Entwürfe")
        val videoFiles = rootFolder.listFiles()?.filter { it.isFile && it.extension == "mp4" } ?: listOf()

        videoListAdapter = VideoListAdapter(this, videoFiles) { video ->
            openVideoPlayerActivity(video)
        }
        recyclerView.adapter = videoListAdapter
    }

    private fun openVideoPlayerActivity(video: File) {
        val intent = Intent(this, VideoPlayerActivity::class.java)
        intent.putExtra("videoFilePath", video.absolutePath)
        startActivity(intent)
    }
}
