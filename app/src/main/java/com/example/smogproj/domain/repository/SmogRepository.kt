package com.example.smogproj.domain.repository

import com.example.smogproj.domain.model.Station
import com.example.smogproj.domain.model.StationDetail
import com.example.smogproj.util.Resource
import kotlinx.coroutines.flow.Flow

interface SmogRepository {

    suspend fun getAllStationsAndSaveToDb(): Flow<Resource<Boolean>>
    suspend fun getStationsFromDb(): Flow<Resource<List<Station>>>

    suspend fun getStationDetails(id: Int): Flow<Resource<StationDetail>>
    suspend fun getStationFromDb(id: Int): Station?

    suspend fun searchStationsByCity(city: String): Flow<Resource<List<Station>>>
}