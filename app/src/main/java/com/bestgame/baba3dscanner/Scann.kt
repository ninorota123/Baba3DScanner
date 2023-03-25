package com.bestgame.baba3dscanner

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import android.util.Log

class Scann : AppCompatActivity() {

    private val cameraRequestCode = 200
    private var projectName: String? = null
    private var videoFilePath: String? = null
    private var videoFileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scann)

        requestPermissions()

        val projectNameEditText = findViewById<EditText>(R.id.project_name_edit_text)
        val okButton = findViewById<Button>(R.id.ok_button)

        okButton.setOnClickListener {
            projectName = projectNameEditText.text.toString()
            val folderPath = "${getExternalFilesDir(Environment.DIRECTORY_MOVIES)}/Baba3DScanner/Entwürfe" // Änderung hier
            val folder = File(folderPath)
            if (!folder.exists()) {
                folder.mkdirs()
            }
            startCamera()
        }
    }

    private fun startCamera() {
        val videoFileName = "$projectName.mp4" // Änderung hier
        val videoFile = File(getExternalFilesDir(Environment.DIRECTORY_MOVIES), "Baba3DScanner/Entwürfe/$videoFileName") // Änderung hier

        videoFilePath = videoFile.absolutePath
        videoFileUri = FileProvider.getUriForFile(this, "$packageName.provider", videoFile)

        Log.d("ScannActivity", "Video file path: $videoFilePath")
        Log.d("ScannActivity", "Video file URI: $videoFileUri")

        // Starte die Kamera-App
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, videoFileUri)
        startActivityForResult(intent, cameraRequestCode)
    }


    @Deprecated("Deprecated in Java")
    @SuppressLint("Recycle")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequestCode && resultCode == Activity.RESULT_OK) {
            // Video wurde erfolgreich aufgenommen und gespeichert

            // Überprüfe, ob das Video tatsächlich am richtigen Ort gespeichert wurde
            var videoFile = File(videoFilePath)
            Log.d("ScannActivity", "Checking video file existence: ${videoFile.exists()}")

            if (!videoFile.exists()) {
                val uri = data?.data
                Log.d("ScannActivity", "Alternative video file URI: $uri")

                if (uri != null) {
                    // Das Video wurde an einem anderen Speicherort gespeichert
                    val inputStream = contentResolver.openInputStream(uri)
                    val outputStream = FileOutputStream(videoFile)
                    if (inputStream != null) {
                        inputStream.copyTo(outputStream)
                    }

                    // Lösche das ursprüngliche Video
                    contentResolver.delete(uri, null, null)
                }
            }

            // Starte die VideoPlayerActivity
            val intent = Intent(this, VideoPlayerActivity::class.java)
            intent.putExtra("videoFilePath", videoFilePath) // Änderung hier
            startActivity(intent)

        } else {
            // Video-Aufnahme wurde abgebrochen oder ist fehlgeschlagen
            // TODO: Fehlerbehandlung implementieren
        }
    }


    private fun requestPermissions() {
        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        ActivityCompat.requestPermissions(this, permissions, 0)
    }
}

