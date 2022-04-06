package com.maxence.photolist

import android.net.Uri

data class PhotoItem(
    val id: String,
    val title: String,
    val uri: Uri
)