package com.example.smogproj.domain.model

import com.example.smogproj.data.remote.dto.Param
import com.example.smogproj.data.remote.dto.Value

data class Position(
    val paramCode: String,
    val paramName: String,
    val measurements: List<Value>? = null
)
