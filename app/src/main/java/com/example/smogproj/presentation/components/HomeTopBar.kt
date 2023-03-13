package com.example.smogproj.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.smogproj.R
import com.example.smogproj.ui.theme.DarkBlue
import com.example.smogproj.ui.theme.DeepBlueSky

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    onSearchClick: () -> Unit
){
    CenterAlignedTopAppBar(
        navigationIcon = {
            Image(
                painterResource(id = R.drawable.smog),
                contentDescription = "appBar logo",
                modifier = Modifier
                    .size(30.dp)
            )
        },
        title = { Text(
            text = "Smog App",
            fontWeight = FontWeight.SemiBold,
            color = DarkBlue
        ) },
        actions = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search icon",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable { onSearchClick() }
            )
        }
    )
}