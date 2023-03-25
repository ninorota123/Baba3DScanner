package com.bestgame.baba3dscanner

import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class ModelCalculationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_model_calculation)

        val videoName = intent.getStringExtra("videoName")

        if (videoName != null) {
            // Fügen Sie hier den Code für die 3D-Berechnungen hinzu

            // Erstellen Sie eine leere .obj-Datei
            val modelsFolderPath = "${getExternalFilesDir(Environment.DIRECTORY_MOVIES)}/Baba3DScanner/Modelle"
            val modelsFolder = File(modelsFolderPath)
            val objFile = File(modelsFolder, "$videoName.obj")
            if (!objFile.exists()) {
                objFile.createNewFile()
            }
        }
    }
}

