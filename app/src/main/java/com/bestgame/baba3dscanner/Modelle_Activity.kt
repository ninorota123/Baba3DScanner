package com.bestgame.baba3dscanner

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class Modelle_Activity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modelle)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val models = getModelFiles()
        val adapter = ModelListAdapter(this, models) { model ->
            // Hier können Sie den Code hinzufügen, um das ausgewählte 3D-Modell anzuzeigen
        }
        recyclerView.adapter = adapter
    }

    private fun getModelFiles(): List<File> {
        val modelsFolderPath = "${getExternalFilesDir(Environment.DIRECTORY_MOVIES)}/Baba3DScanner/Modelle"
        val modelsFolder = File(modelsFolderPath)
        return modelsFolder.listFiles()?.filter { file ->
            file.isFile && file.extension.lowercase() == "obj"
        }?.toList() ?: emptyList()
    }
}