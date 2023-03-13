package com.example.smogproj.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smogproj.R
import com.example.smogproj.presentation.components.PositionItem

@Composable
fun DetailContent(
    paddingValues: PaddingValues,
    viewModel: DetailViewModel
){
    val stationDetail = viewModel.stationDetail

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
    ) {
        if (!viewModel.isLoading && stationDetail != null) {
            Card(
                modifier = Modifier.padding(horizontal = 8.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 16.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 4.dp, bottom = 8.dp, end = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = stationDetail.station?.stationName ?: "Nieznane",
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Miejscowość",
                            fontSize = 16.sp
                        )
                        Text(
                            text = stationDetail.station?.city?.name ?: "Nieznane",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Powiat:" ,
                            fontSize = 16.sp
                        )
                        Text(
                            text = stationDetail.station?.city?.commune?.districtName ?: "Nieznane",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Województwo:",
                            fontSize = 16.sp
                        )
                        Text(
                            text = stationDetail.station?.city?.commune?.provinceName ?: "Nieznane",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    }
                    Image(
                        painterResource(id = R.drawable.station),
                        contentDescription = "station image",
                        modifier = Modifier
                            .align(CenterVertically)
                            .height(100.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Stanowiska pomiarowe",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            stationDetail.positions.forEach { position ->
                PositionItem(position = position)
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            if(viewModel.isLoading){
                CircularProgressIndicator()
            } else if (viewModel.error != ""){
                Text(
                    text = viewModel.error,
                    color = Color.Red
                )
            }
        }
    }
}