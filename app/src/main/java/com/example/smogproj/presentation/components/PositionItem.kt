package com.example.smogproj.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smogproj.data.remote.dto.Value
import com.example.smogproj.domain.model.Position
import com.example.smogproj.ui.theme.*
import com.example.smogproj.util.Tools.formatDateString
import com.example.smogproj.util.Tools.roundDouble

@Composable
fun PositionItem(
    position: Position
){
    var expandedCard by remember{ mutableStateOf(true) }

    Card(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .clickable { expandedCard = !expandedCard },
        colors = CardDefaults.cardColors(
            containerColor = DeepBlueSky
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = position.paramName,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
                Icon(
                    imageVector = if(expandedCard) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = "arrow icon"
                )
            }
            AnimatedVisibility(visible = expandedCard) {
                Column() {
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = position.paramCode,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    if(position.measurements != null){
                        LazyRow(){
                            items(items = position.measurements){ measurement ->
                                MeasurementItem(measurement = measurement)
                            }
                        }
                    }else{
                        Text(text = "Nie udało się pobrać pomiarów dla danego stanowiska")
                    }
                }
            }
        }
    }
}

@Composable
fun MeasurementItem(
    measurement: Value
){
    Card(
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = roundDouble(measurement.value),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = formatDateString(measurement.date),
                fontSize = 13.sp
            )
        }
    }

}
