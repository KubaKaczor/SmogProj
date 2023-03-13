package com.example.smogproj.data.repository

import android.provider.ContactsContract
import com.example.smogproj.data.remote.dto.City
import com.example.smogproj.data.remote.dto.Commune
import com.example.smogproj.domain.model.Station
import com.example.smogproj.domain.model.StationDetail
import com.example.smogproj.domain.repository.SmogRepository
import com.example.smogproj.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSmogRepository: SmogRepository {

    private val stations = mutableListOf<Station>()

    override suspend fun getAllStationsAndSaveToDb(): Flow<Resource<Boolean>> {
        return flow{
            val stationsToInsert = mutableListOf<Station>()
            ('a'..'z').forEachIndexed { index, c ->
                val province = if(c in 'a'..'f') "Malopolskie" else if(c in 'g'..'k') "Wielkopolskie" else if(c in 'l'..'p') "dolnoslaskie" else "mazowieckie"
                val cityName = if(c in 'a'..'f') "Krakow" else if(c in 'g'..'k') "Poznan" else if(c in 'l'..'p') "Wroclaw" else "Warszawa"
                stationsToInsert.add(
                    Station(
                        addressStreet = "$c",
                        city = City(
                            Commune(
                                communeName = "",
                                provinceName = province,
                                districtName = ""
                            ),
                            id = 5,
                            name = cityName
                        ),
                        gegrLat = "59.569",
                        gegrLon = "62.639",
                        id = index,
                        stationName = "abc",
                    )
                )
            }
            stations.addAll(stationsToInsert)
            Resource.Success(true)
        }
    }

    override suspend fun getStationsFromDb(): Flow<Resource<List<Station>>> {
        return flow{
            emit(Resource.Success(stations))
        }
    }

    override suspend fun getStationDetails(id: Int): Flow<Resource<StationDetail>> {
        TODO("Not yet implemented")
    }

    override suspend fun getStationFromDb(id: Int): Station? {
        return stations.find { it.id == id }
    }

    override suspend fun searchStationsByCity(city: String): Flow<Resource<List<Station>>> {
        return flow{
            val stationsFound = stations.filter { it.city!!.name.contains(city) }
            emit(Resource.Success(stationsFound))
        }
    }
}