package com.example.smogproj.presentation.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smogproj.presentation.components.ScreenTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    onBackClick: () -> Unit
){
    val viewModel: DetailViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            ScreenTopBar(label = "Stacja pomiarowa") {
                onBackClick()
            }
        },
        content = {
            DetailContent(
                paddingValues = it,
                viewModel = viewModel
            )
        }
    )
}