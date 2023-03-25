package com.bestgame.baba3dscanner

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import android.os.Environment
import java.io.File

class VideoPlayerActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val videoView = findViewById<VideoView>(R.id.videoView)
        val videoFilePath = intent.getStringExtra("videoFilePath")

        if (videoFilePath != null) {
            val videoUri = Uri.parse(videoFilePath)
            videoView.setVideoURI(videoUri)

            // Fügen Sie hier den MediaController hinzu
            val mediaController = MediaController(this)
            mediaController.setAnchorView(videoView)
            videoView.setMediaController(mediaController)

            videoView.start()
        }

        val calculate3dModelButton = findViewById<Button>(R.id.calculate3dModelButton)
        calculate3dModelButton.setOnClickListener {
            val modelsFolderPath = "${getExternalFilesDir(Environment.DIRECTORY_MOVIES)}/Baba3DScanner/Modelle"
            val modelsFolder = File(modelsFolderPath)
            if (!modelsFolder.exists()) {
                modelsFolder.mkdirs()
            }
            // Starten von ModelCalculationActivity
            val intent = Intent(this, ModelCalculationActivity::class.java)
            startActivity(intent)
        }
    }
}
