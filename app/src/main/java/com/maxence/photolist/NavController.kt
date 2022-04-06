package com.maxence.photolist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavComponent() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val model : PhotoViewModel = viewModel(factory = PhotoViewModel.provideFactory())

    val scaffoldState: ScaffoldState = rememberScaffoldState(
        snackbarHostState = SnackbarHostState()
    )
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    val currentDestination = getNavDestination(navController = navController)
                    if (currentDestination?.hierarchy?.any { it.route == "home" } != true) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Filled.ArrowBack, "", tint = Color.White)
                        }
                    }
                    Text(text = "Picture", color = Color.White)
                },
                backgroundColor = Color(0xFF164255),
            )
        },
        floatingActionButton = {
            val currentDestination = getNavDestination(navController = navController)
            if (currentDestination?.hierarchy?.any { it.route == "home" } == true) {
                IconButton(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFe05657)),
                    onClick = { navController.navigate("takePicture") }) {
                    Icon(Icons.Filled.Add, "", tint = Color.White)
                }
            }
        },
        content = {
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomeView(navController, model) }
                composable("takepicture") { TakePictureView(navController, model) }
            }
        },
        backgroundColor = Color(0xFFE1E2E1),
    )
}

@Composable
fun getNavDestination(navController: NavHostController): NavDestination? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination
}