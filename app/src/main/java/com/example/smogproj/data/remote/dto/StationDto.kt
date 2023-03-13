package com.example.smogproj.data.remote.dto

data class StationDto(
    val addressStreet: String? = null,
    val city: City? = null,
    val gegrLat: String? = null,
    val gegrLon: String? = null,
    val id: Int,
    val stationName: String? = null
)