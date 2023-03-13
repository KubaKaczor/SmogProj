package com.example.smogproj.domain.model

import com.example.smogproj.data.remote.dto.City
import com.example.smogproj.data.remote.dto.Commune

data class Station(
    val addressStreet: String,
    val city: City? = null,
    val gegrLat: String,
    val gegrLon: String,
    val id: Int,
    val stationName: String,
)
