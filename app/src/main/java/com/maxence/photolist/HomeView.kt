package com.maxence.photolist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController, viewModel: PhotoViewModel) {
    HomeView(navController = navController, photos = viewModel.listPhoto)
}

@Composable
fun HomeView(navController: NavHostController, photos: List<PhotoItem>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                photos.forEach {
                    PhotoRaw(photo = it) {
                        navController.navigate("detail/${it.id}")
                    }
                }
            }
        }
    }
}