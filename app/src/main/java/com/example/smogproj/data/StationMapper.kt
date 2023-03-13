package com.example.smogproj.data

import com.example.smogproj.data.local.StationEntity
import com.example.smogproj.data.remote.dto.City
import com.example.smogproj.data.remote.dto.Commune
import com.example.smogproj.data.remote.dto.StationDto
import com.example.smogproj.domain.model.Station

fun StationEntity.toStation(): Station{
    return Station(
        id = id!!,
        stationName = stationName,
        city = City(
            commune = Commune(
                communeName = communeName,
                districtName = districtName,
                provinceName = provinceName
            ),
            id = -1,
            name = city
        ),
        gegrLon = "",
        gegrLat = "",
        addressStreet = ""
    )
}

fun StationDto.toStationEntity(): StationEntity{
    return StationEntity(
        id = id,
        city = city?.name ?: "Nieznane",
        stationName = stationName ?: "Nieznane",
        communeName = city?.commune?.communeName ?: "Nieznane",
        districtName = city?.commune?.districtName ?: "Nieznane",
        provinceName = city?.commune?.provinceName ?: "Nieznane",
    )
}