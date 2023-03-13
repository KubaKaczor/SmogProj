package com.example.smogproj.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smogproj.domain.model.Station
import com.example.smogproj.domain.repository.SmogRepository
import com.example.smogproj.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SmogRepository
): ViewModel() {

    var isLoading by mutableStateOf(false)
    var error by mutableStateOf("")
    var searchQuery by mutableStateOf("")
    var stations by mutableStateOf(emptyList<Station>())

    fun changeQuery(value: String){
        searchQuery = value
    }

    fun searchStationsByCity(){
        viewModelScope.launch {
            repository
                .searchStationsByCity(searchQuery)
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
}