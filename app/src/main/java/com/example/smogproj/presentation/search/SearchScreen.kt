package com.example.smogproj.presentation.search

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smogproj.presentation.components.ScreenTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    onBackClick: () -> Unit,
    onDetailClick: (Int) -> Unit
){
    val viewModel: SearchViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            ScreenTopBar(label = "Szukaj") {
                onBackClick()
            }
        },
        content = {
            SearchContent(
                paddingValues = it,
                viewModel = viewModel
            ){ stationId ->
                onDetailClick(stationId)
            }
        }
    )
}