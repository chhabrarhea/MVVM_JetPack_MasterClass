package com.example.mvvm_jetpack_masterclass.camerax

import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mvvm_jetpack_masterclass.R
import kotlinx.android.synthetic.main.activity_preview.*
import java.io.File
import java.security.Permission
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.jar.Manifest

class PreviewActivity : AppCompatActivity() {
    private var imageCapture: ImageCapture? = null
    lateinit var video:VideoView
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
        video=findViewById(R.id.video)

        val mediaController=MediaController(this)
        mediaController.setAnchorView(video)
        video.setMediaController(mediaController)
        val uri=Uri.parse("http://d27wqx0d95irnd.cloudfront.net/bueno_main")
        video.setMediaController(MediaController(this))
        video.setVideoURI(uri)


            // Request camera permissions
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                ActivityCompat.requestPermissions(
                    this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
            }

//            // Set up the listener for take photo button
//            camera_capture_button.setOnClickListener { takePhoto() }

            outputDirectory = getOutputDirectory()

            cameraExecutor = Executors.newSingleThreadExecutor()
        }

        private fun takePhoto() {
            val imageCapture = imageCapture ?: return

            // Create time-stamped output file to hold the image
            val photoFile = File(
                outputDirectory,
                SimpleDateFormat(FILENAME_FORMAT, Locale.US
                ).format(System.currentTimeMillis()) + ".jpg")

            // Create output options object which contains file + metadata
            val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

            // Set up image capture listener, which is triggered after photo has
            // been taken
            imageCapture.takePicture(
                outputOptions, ContextCompat.getMainExecutor(this), object : ImageCapture.OnImageSavedCallback {
                    override fun onError(exc: ImageCaptureException) {
                        Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                    }

                    override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                        val savedUri = Uri.fromFile(photoFile)
                        val msg = "Photo capture succeeded: $savedUri"
                        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                        Log.d(TAG, msg)
                    }
                })
        }

        private fun startCamera() {
            val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

            cameraProviderFuture.addListener(Runnable {
                // Used to bind the lifecycle of cameras to the lifecycle owner
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

                // Preview
                val preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(viewFinder.surfaceProvider)
                    }

                // Select back camera as a default
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    // Unbind use cases before rebinding
                    cameraProvider.unbindAll()

                    // Bind use cases to camera
                    cameraProvider.bindToLifecycle(
                        this, cameraSelector, preview, imageCapture)

                } catch(exc: Exception) {
                    Log.e(TAG, "Use case binding failed", exc)
                }

            }, ContextCompat.getMainExecutor(this))
            imageCapture = ImageCapture.Builder()
                .build()
        }

        private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                baseContext, it) == PackageManager.PERMISSION_GRANTED
        }

        private fun getOutputDirectory(): File {
            val mediaDir = externalMediaDirs.firstOrNull()?.let {
                File(it, resources.getString(R.string.app_name)).apply { mkdirs() } }
            return if (mediaDir != null && mediaDir.exists())
                mediaDir else filesDir
        }

        override fun onDestroy() {
            video.stopPlayback()
            super.onDestroy()
            cameraExecutor.shutdown()
        }

        companion object {
            private const val TAG = "CameraXBasic"
            private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
            private const val REQUEST_CODE_PERMISSIONS = 10
            private val REQUIRED_PERMISSIONS = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
    fun playVideo(v: View)
    {
        val b: Button =v as Button
        if(!video.isPlaying)
        {
            video.visibility=View.VISIBLE
            video.setZOrderOnTop(true)
            video.start()
            b.text="Stop Video"
        }
        else
        {
            video.visibility=View.INVISIBLE
            video.stopPlayback()
            b.text="Play Video"
        }
    }

    override fun onPause() {
        video.stopPlayback()
        super.onPause()
    }

    }