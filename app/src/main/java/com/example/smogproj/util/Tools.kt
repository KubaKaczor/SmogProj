package com.example.smogproj.util

import com.example.smogproj.domain.model.Station
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Tools {

    fun roundDouble(value: Double): String{
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        return df.format(value)
    }

    fun formatDateString(date: String): String{
        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return LocalDate.parse(date , format).toString()
    }

    fun groupStationsByProvince(stations: List<Station>): Map<String, List<Station>>{
        return stations.groupBy { it.city?.commune?.provinceName!! }
    }
}