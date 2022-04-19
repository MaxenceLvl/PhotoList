package com.maxence.photolist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.rememberImagePainter
import java.io.File

@Composable
fun DetailScreen(photoId:String, viewModel: PhotoViewModel) {
    DetailView(photoPath = photoId, directory = viewModel.outputDirectory)
}

@Composable
fun DetailView(photoPath: String, directory: File) {
    val uri = File("${directory.absolutePath}/$photoPath").toUri()
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Image(
            painter = rememberImagePainter(
                data  = uri.toString()
            ),
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(5.dp)),
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = photoPath)
    }
}

@Preview(showBackground = true)
@Composable
fun DetailView_Preview() {
    DetailView(
        photoPath = "photo.jpg", directory = File("/storage/emulated/0/Pictures/PhotoList/")
    )
}