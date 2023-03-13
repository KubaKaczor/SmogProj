package com.example.smogproj.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smogproj.presentation.components.StationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchContent(
    paddingValues: PaddingValues,
    viewModel: SearchViewModel,
    onDetailClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
    ) {
        OutlinedTextField(
            value = viewModel.searchQuery,
            onValueChange = {
                viewModel.changeQuery(it)
            },
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Podaj nazwę miasta...",)
            },
            maxLines = 1,
            singleLine = true,
            keyboardActions = KeyboardActions{
                viewModel.searchStationsByCity()
            },
            shape = RoundedCornerShape(16.dp)
        )

        if(viewModel.isLoading){
            LinearProgressIndicator(modifier = Modifier.then(Modifier.fillMaxWidth()))
        }
        else{
            LazyColumn(){
                items(items = viewModel.stations){ station ->
                    StationItem(station = station){
                        onDetailClick(station.id)
                    }
                }
            }
        }
    }
}
