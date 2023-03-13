package com.example.smogproj.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smogproj.domain.model.Station
import com.example.smogproj.domain.repository.SmogRepository
import com.example.smogproj.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: SmogRepository
): ViewModel(){

    var isLoading by mutableStateOf(false)
    var isRefreshing by mutableStateOf(false)
    var error by mutableStateOf("")
    var stations by mutableStateOf(emptyList<Station>())

    init {
        getStations()
    }

    private fun getStations(){
        viewModelScope.launch {
            repository
                .getStationsFromDb()
                .collect { result ->
                    when(result){
                        is Resource.Success ->{
                            result.data?.let{ stationsResult ->
                                stations = stationsResult
                            }
                            isLoading = false
                        }
                        is Resource.Error -> {
                            error = result.message!!
                            isLoading = false
                        }
                        is Resource.Loading ->{
                            isLoading = result.isLoading
                        }
                    }
                }
        }
    }


    fun refreshStations(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllStationsAndSaveToDb().collect()
        }
    }

}