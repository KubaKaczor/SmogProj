package com.example.smogproj.data.remote

import com.example.smogproj.data.remote.dto.MeasurementDto
import com.example.smogproj.data.remote.dto.PositionDto
import com.example.smogproj.data.remote.dto.StationsResult
import retrofit2.http.GET
import retrofit2.http.Path

interface SmogApi {

    @GET("station/findAll")
    suspend fun getAllStations(): StationsResult

    @GET("station/sensors/{stationId}")
    suspend fun getStationPositions(
        @Path("stationId") id: Int,
    ): List<PositionDto>

    @GET("data/getData/{sensorId}")
    suspend fun getMeasurements(
        @Path("sensorId") id: Int,
    ): MeasurementDto

    companion object{
        const val BASE_URL = "https://api.gios.gov.pl/pjp-api/rest/"
    }
}