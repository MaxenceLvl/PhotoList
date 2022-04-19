package com.maxence.photolist

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.io.File
import java.util.concurrent.ExecutorService

class PhotoViewModel(val application: Application): ViewModel() {

    var outputDirectory: File = setOutputDirectory()
    private lateinit var photoUri: Uri

    private var shouldShowPhoto: MutableState<Boolean> = mutableStateOf(false)
    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)
    val listPhoto: MutableList<PhotoItem> = mutableListOf()


    fun addPhoto(photo: PhotoItem) {
        listPhoto.add(photo)
    }

    private fun getOutputDirectory() {
        val mediaDir = application.externalMediaDirs.firstOrNull()?.let {
            File(it, application.getString(R.string.app_name)).apply { mkdirs() }
        }

        outputDirectory = if (mediaDir != null && mediaDir.exists()) mediaDir else application.filesDir
    }

    private fun handleImageCapture(uri: Uri) {
        Log.i("kilo", "Image captured: $uri")
        shouldShowCamera.value = false
        photoUri = uri
        shouldShowPhoto.value = true
    }
}