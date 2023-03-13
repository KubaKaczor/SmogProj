package com.example.smogproj.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StationDao {

    @Insert
    suspend fun insertStations(stations: List<StationEntity>)

    @Query("Delete from stations")
    suspend fun deleteAll()

    @Query("Select * from stations")
    fun getStations(): Flow<List<StationEntity>>

    @Query("Select * from stations WHERE city LIKE '%' || :city || '%'")
    fun getStationsByCity(city: String): Flow<List<StationEntity>>

    @Query("Select * from stations where id=:id")
    suspend fun getStation(id: Int): StationEntity
}