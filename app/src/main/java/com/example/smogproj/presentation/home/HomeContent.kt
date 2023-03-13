package com.example.smogproj.presentation.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smogproj.presentation.components.StationItem
import com.example.smogproj.util.Tools.groupStationsByProvince
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel,
    onDetailClick: (Int) -> Unit
) {
    val groupedStations = groupStationsByProvince(viewModel.stations)
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.isRefreshing
    )

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            viewModel.refreshStations()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
                //.padding(8.dp)
        ) {
            Text(
                text = "Stacje pomiarowe",
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))


            if (!viewModel.isLoading && viewModel.stations.isNotEmpty()) {
                LazyColumn {
                    groupedStations.forEach { (province, stations) ->
                        stickyHeader(key = province) {
                            ProvinceHeader(province = province!!)
                        }
                        items(items = stations, key = { it.id }) { station ->
                            StationItem(
                                station = station,
                                onClick = {
                                    onDetailClick(station.id)
                                }
                            )
                        }
                    }
                }

            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (viewModel.isLoading) {
                    CircularProgressIndicator()
                } else if (viewModel.error != "") {
                    Text(
                        text = viewModel.error,
                        color = Color.Red
                    )
                }
            }
        }
    }
}

@Composable
fun ProvinceHeader(province: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            //.clip(RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
            .padding(vertical = 14.dp),
        //shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
    ) {
        Text(
            text = province,
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier
                .padding(start = 8.dp)
                .background(Color.White)
        )
    }
}