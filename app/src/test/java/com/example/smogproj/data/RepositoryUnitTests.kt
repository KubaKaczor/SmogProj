package com.example.smogproj.data

import android.provider.ContactsContract
import com.example.smogproj.data.repository.FakeSmogRepository
import com.example.smogproj.util.Tools.groupStationsByProvince
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RepositoryUnitTests {

    private lateinit var fakeRepository: FakeSmogRepository

    @Before
    fun setUp() {
        fakeRepository = FakeSmogRepository()

        runBlocking {
            fakeRepository.getAllStationsAndSaveToDb().collect()
        }
    }

    @Test
    fun `Group by province, correct groups`() = runBlocking {
        val stations = fakeRepository.getStationsFromDb().first().data

        groupStationsByProvince(stations!!).forEach { (province, stations) ->
            stations.forEach{ station ->
                Assert.assertEquals(province, station.city!!.commune.provinceName)
            }
        }
    }


    @Test
    fun `Find station by Id, correct station`() = runBlocking {
        ('a'..'z').forEachIndexed {  index, _ ->
            val station = fakeRepository.getStationFromDb(index)
            Assert.assertEquals(index, station!!.id)
        }
    }

    @Test
    fun `Find station by Id, incorrect station`() = runBlocking {
        ('a'..'z').forEachIndexed {  index, _ ->
            val station = fakeRepository.getStationFromDb(index)
            Assert.assertNotEquals(index + 1, station!!.id)
        }
    }

    @Test
    fun `Find stations by cityName Krakow, correct stations`() = runBlocking {
        val stations = fakeRepository.searchStationsByCity("Krakow").first().data

        stations!!.forEach{ station ->
            Assert.assertEquals("Krakow", station.city!!.name)
        }

    }

    @Test
    fun `Find stations by cityName Poznan, correct stations`() = runBlocking {
        val stations = fakeRepository.searchStationsByCity("Poznan").first().data

        stations!!.forEach{ station ->
            Assert.assertEquals("Poznan", station.city!!.name)
        }

    }

    @Test
    fun `Find stations by cityName like Wro, correct stations`() = runBlocking {
        val stations = fakeRepository.searchStationsByCity("Wro").first().data

        stations!!.forEach{ station ->
            Assert.assertEquals("Wroclaw", station.city!!.name)
        }

    }

    @Test
    fun `Find stations by cityName like Warsz, correct stations`() = runBlocking {
        val stations = fakeRepository.searchStationsByCity("Warsz").first().data

        stations!!.forEach{ station ->
            Assert.assertEquals("Warszawa", station.city!!.name)
        }

    }

    @Test
    fun `Find stations by cityName, incorrect stations, different cityNames`() = runBlocking {
        val stations = fakeRepository.searchStationsByCity("Wroclaw").first().data

        stations!!.forEach{ station ->
            Assert.assertNotEquals("Poznan", station.city!!.name)
        }

    }

    @Test
    fun `Find stations by cityName, incorrect stations, different city names`() = runBlocking {
        val stations = fakeRepository.searchStationsByCity("Poznan").first().data

        stations!!.forEach{ station ->
            Assert.assertNotEquals("Wroclaw", station.city!!.name)
        }

    }

    @Test
    fun `Find stations by cityName, incorrect stations, cityName doesn't contain value`() = runBlocking {
        val stations = fakeRepository.searchStationsByCity("Poznann").first().data

        stations!!.forEach{ station ->
            Assert.assertNotEquals("Poznan", station.city!!.name)
        }

    }

}