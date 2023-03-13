package com.example.smogproj.domain.model

import com.example.smogproj.data.remote.dto.City

data class StationDetail(
    val station: Station?,
    val positions: List<Position>
)
