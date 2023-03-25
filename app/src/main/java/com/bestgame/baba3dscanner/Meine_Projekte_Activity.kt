package com.bestgame.baba3dscanner

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button

class Meine_Projekte_Activity : AppCompatActivity(), CameraCallback {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meine_projekte)

        val modelsButton = findViewById<Button>(R.id.models_button)
        val draftsButton = findViewById<Button>(R.id.drafts_button)

        // Add click listeners to the buttons
        modelsButton.setOnClickListener {
            val intent = Intent(this, Modelle_Activity::class.java)
            startActivity(intent)
        }
        draftsButton.setOnClickListener {
            val intent = Intent(this, Entwuerfe_Activity::class.java)
            startActivity(intent)
        }
    }

    companion object {
        const val REQUEST_VIDEO_CAPTURE = 1
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
}
