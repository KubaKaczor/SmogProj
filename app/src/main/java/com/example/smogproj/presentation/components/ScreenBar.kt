package com.example.smogproj.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.smogproj.ui.theme.DeepBlueSky

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(
    label: String,
    onBack: () -> Unit
){
    CenterAlignedTopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "logo icon",
                modifier = Modifier
                    .clickable { onBack() }
            )
        },
        title = { Text(
            text = label,
            fontWeight = FontWeight.SemiBold
        ) }
    )
}