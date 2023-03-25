package com.bestgame.baba3dscanner

import android.content.Context
import android.opengl.GLES20
import android.view.MotionEvent
import org.rajawali3d.Object3D
import org.rajawali3d.lights.DirectionalLight
import org.rajawali3d.loader.LoaderOBJ
import org.rajawali3d.loader.ParsingException
import org.rajawali3d.materials.Material
import org.rajawali3d.materials.textures.Texture
import org.rajawali3d.math.vector.Vector3
import org.rajawali3d.renderer.Renderer
import org.rajawali3d.util.RajLog
import javax.microedition.khronos.opengles.GL10

class MyOBJRenderer(context: Context, modelPath: String? = null) : Renderer(context) {
    private var modelPath: String? = modelPath
    private lateinit var model: Object3D

    init {
        setFrameRate(60)
    }

    override fun initScene() {
        val light = DirectionalLight(1.0, 0.2, -1.0)
        light.power = 1.0f
        currentScene.addLight(light)

        if (modelPath != null) {
            loadModel(modelPath!!)
        }

        currentCamera.z = 4.0
    }

    fun loadModel(modelPath: String) {
        this.modelPath = modelPath
        val loader = LoaderOBJ(this, modelPath)
        try {
            loader.parse()
            model = loader.parsedObject

            val material = Material()
            val checkerboardTexture = Texture("checkerboard", R.drawable.checkerboard)
            material.addTexture(checkerboardTexture)
            model.material = material

            currentScene.addChild(model)
            model.setScale(0.1)
            model.rotate(Vector3.Axis.Y, 180.0)
        } catch (e: ParsingException) {
            RajLog.e("[Object3D] Parsing Failed: " + e.message)
        }
    }

    override fun onRender(ellapsedRealtime: Long, deltaTime: Double) {
        super.onRender(ellapsedRealtime, deltaTime)
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f) // Setzt die Hintergrundfarbe auf Weiß
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT or GLES20.GL_DEPTH_BUFFER_BIT)
        GLES20.glEnable(GLES20.GL_BLEND)
        GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA)
    }

    override fun onTouchEvent(event: MotionEvent): Unit {
        // Implementieren Sie hier die gewünschte Touch-Interaktion
    }

    override fun onOffsetsChanged(xOffset: Float, yOffset: Float, xOffsetStep: Float, yOffsetStep: Float, xPixelOffset: Int, yPixelOffset: Int) {
        // Not used, but needs to be implemented
    }
}
