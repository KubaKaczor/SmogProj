package com.example.smogproj.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stations")
data class StationEntity(
    @PrimaryKey
    val id: Int? = null,
    val stationName: String,
    val city: String,
    val communeName: String,
    val districtName: String,
    val provinceName: String
)
