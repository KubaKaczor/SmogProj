package com.example.smogproj.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smogproj.domain.model.Station
import com.example.smogproj.ui.theme.DarkBlue
import com.example.smogproj.ui.theme.DeepBlueSky
import com.example.smogproj.ui.theme.LightBlue
import com.example.smogproj.ui.theme.LightBlueSky

@Composable
fun StationItem(
    station: Station,
    onClick: (Int) -> Unit
){
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 12.dp)
            .fillMaxWidth()
            .clickable {
                onClick(station.id)
            },
        colors = CardDefaults.cardColors(
            containerColor = LightBlue
        )
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = com.example.smogproj.R.drawable.thermometr),
                contentDescription = "appBar logo",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column() {
                Text(
                    text = station.stationName,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = "Powiat: " + (station.city?.commune?.districtName ?: "Nieznane"),
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }

}