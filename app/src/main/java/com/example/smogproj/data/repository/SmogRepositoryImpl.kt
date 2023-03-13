package com.example.smogproj.data.repository

import com.example.smogproj.data.local.StationDao
import com.example.smogproj.data.remote.SmogApi
import com.example.smogproj.data.toStation
import com.example.smogproj.data.toStationEntity
import com.example.smogproj.domain.model.Station
import com.example.smogproj.domain.model.Position
import com.example.smogproj.domain.model.StationDetail
import com.example.smogproj.domain.repository.SmogRepository
import com.example.smogproj.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SmogRepositoryImpl @Inject constructor(
    private val api: SmogApi,
    private val dao: StationDao
): SmogRepository {

    override suspend fun getAllStationsAndSaveToDb(): Flow<Resource<Boolean>>{
        return flow {
            emit(Resource.Loading(true))
            val remoteResult = try{
                api.getAllStations()
            }catch (e: IOException){
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data. ${e.message}"))
                null
            }catch (e: HttpException){
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data. ${e.message}"))
                null
            }

            remoteResult?.let{ result ->
                val stations =  result.map { it.toStationEntity() }
                dao.deleteAll()
                dao.insertStations(stations)
                emit(Resource.Success(data = true))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getStationsFromDb(): Flow<Resource<List<Station>>> {
        return flow{
            emit(Resource.Loading(isLoading = true))
            val stations = try{
                dao.getStations()
            }catch (e: IOException){
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data. ${e.message}"))
                null
            }catch (e: HttpException){
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data ${e.message}"))
                null
            }

            stations?.let{ result ->
                result.collect{ stationsEntities ->
                    emit(Resource.Success(
                        data = stationsEntities.map { it.toStation() }
                    ))
                }
                emit(Resource.Loading(isLoading = false))
            }
        }
    }

    override suspend fun getStationDetails(id: Int): Flow<Resource<StationDetail>> {
        return flow{
            emit(Resource.Loading(isLoading = true))

            val remoteResult = try{
                api.getStationPositions(id)
            }catch (e: IOException){
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data. ${e.message}"))
                null
            }catch (e: HttpException){
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data ${e.message}"))
                null
            }

            val positions = remoteResult?.let{ result ->
                result.map { position ->
                    val measurementsResult = try{
                        api.getMeasurements(position.id)
                    }catch (e: IOException){
                        e.printStackTrace()
                        emit(Resource.Error(message = "Couldn't load data. ${e.message}"))
                        null
                    }catch (e: HttpException){
                        e.printStackTrace()
                        emit(Resource.Error(message = "Couldn't load data ${e.message}"))
                        null
                    }

                    val values = measurementsResult?.values
                    Position(
                        paramCode = position.param.paramCode,
                        paramName = position.param.paramName,
                        measurements = values
                    )
                }
            }

            positions?.let{ result ->
                emit(Resource.Success(data =
                    StationDetail(
                        station = getStationFromDb(id),
                        positions = result
                    )
                ))
                emit(Resource.Loading(isLoading = false))
            }
        }
    }

    override suspend fun getStationFromDb(id: Int): Station? {
        val stationResult = try {
            dao.getStation(id)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: HttpException) {
            e.printStackTrace()
            null
        }

        return stationResult?.let {
            it.toStation()
        }
    }

    override suspend fun searchStationsByCity(city: String): Flow<Resource<List<Station>>> {
        return flow{
            emit(Resource.Loading(isLoading = true))
            val stations = try{
                dao.getStationsByCity(city)
            }catch (e: IOException){
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data. ${e.message}"))
                null
            }catch (e: HttpException){
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data ${e.message}"))
                null
            }

            stations?.let{ result ->
                result.collect{ stationsEntities ->
                    emit(Resource.Success(
                        data = stationsEntities.map { it.toStation() }
                    ))
                }
                emit(Resource.Loading(isLoading = false))
            }
        }
    }
}