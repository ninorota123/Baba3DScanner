package com.bestgame.baba3dscanner

import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.rajawali3d.view.SurfaceView
import java.io.File

class Modelle_Activity : AppCompatActivity() {

    private lateinit var renderer: MyOBJRenderer
    private lateinit var surfaceView: SurfaceView
    private var isRendererSet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modelle)

        surfaceView = findViewById(R.id.rajawali_surface)

        // Initialisiere den Renderer mit einer leeren Szene
        renderer = MyOBJRenderer(this)
        surfaceView.setSurfaceRenderer(renderer)
        isRendererSet = true

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val models = getModelFiles()
        val adapter = ModelListAdapter(this, models) { model ->
            // Lade das ausgewählte 3D-Modell in den Renderer
            renderer.loadModel(model.absolutePath)
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

    override fun onResume() {
        super.onResume()
        if (isRendererSet) {
            surfaceView.onResume()
        }
    }

    override fun onPause() {
        if (isRendererSet) {
            surfaceView.onPause()
        }
        super.onPause()
    }
}
