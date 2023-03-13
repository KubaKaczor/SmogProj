package com.example.smogproj.presentation.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smogproj.presentation.components.HomeTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onSearchClick: () -> Unit,
    onDetailClick: (Int) -> Unit
){
    val viewModel: HomeViewModel = hiltViewModel()

    Scaffold(
        topBar = {
            HomeTopBar(){
                onSearchClick()
            }
        },
        content = {
            HomeContent(
                paddingValues = it,
                viewModel = viewModel
            ){ stationId ->
                onDetailClick(stationId)
            }
        }
    )

}