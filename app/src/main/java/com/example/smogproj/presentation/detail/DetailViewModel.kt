package com.example.smogproj.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smogproj.domain.model.StationDetail
import com.example.smogproj.domain.repository.SmogRepository
import com.example.smogproj.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: SmogRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var isLoading by mutableStateOf(false)
    var error by mutableStateOf("")
    var stationDetail by mutableStateOf<StationDetail?>(null)

    init {
        getStationDetail()
    }

    private fun getStationDetail(){
        viewModelScope.launch {
            val id = savedStateHandle.get<Int>("stationId") ?: return@launch
            repository
                .getStationDetails(id)
                .collect { result ->
                    when(result){
                        is Resource.Success ->{
                            result.data?.let{ result ->
                                stationDetail = result
                                isLoading = false
                            }
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