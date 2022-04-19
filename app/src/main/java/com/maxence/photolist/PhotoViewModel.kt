package com.maxence.photolist

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.io.File

class PhotoViewModel(private val application: Application) : ViewModel() {

    var outputDirectory: File = setOutputDirectory()
    private lateinit var photoUri: Uri

    val listPhoto: MutableList<PhotoItem> = mutableListOf()

    private fun setOutputDirectory(): File {
        val mediaDir = application.externalMediaDirs.firstOrNull()?.let {
            File(it, application.getString(R.string.app_name)).apply { mkdirs() }
        }

        return if (mediaDir != null && mediaDir.exists()) mediaDir else application.filesDir
    }

    fun fetchPhotos() {
        listPhoto.clear()
        val files = outputDirectory.listFiles()
        if (files != null) {
            for (file in files) {
                Log.d("PhotoViewModel", "file: $file")
                listPhoto.add(PhotoItem(id = file.name, title = file.absolutePath, uri = file.toUri()))
            }
        }
    }

    fun handleImageCapture(uri: Uri) {
        Log.i("PhotoList", "Image captured: $uri")
        photoUri = uri
    }
}