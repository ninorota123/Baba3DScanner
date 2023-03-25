package com.bestgame.baba3dscanner

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button

class MainActivity : AppCompatActivity(), CameraCallback {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scanButton = findViewById<Button>(R.id.scan_button)
        val projectsButton = findViewById<Button>(R.id.projects_button)

        // Add click listeners to the buttons
        scanButton.setOnClickListener {
            val intent = Intent(this, Scann::class.java)
            startActivity(intent)
        }
        projectsButton.setOnClickListener {
            val intent = Intent(this, Meine_Projekte_Activity::class.java)
            startActivity(intent)
        }
    }

    override fun dispatchTakeVideoIntent() {
        val takeVideoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        if (takeVideoIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            // TODO: Handle the captured video
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        const val REQUEST_VIDEO_CAPTURE = 1
    }
}

interface CameraCallback {
    fun dispatchTakeVideoIntent()
}
