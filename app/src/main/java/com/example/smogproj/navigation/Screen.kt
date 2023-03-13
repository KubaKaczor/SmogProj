package com.example.smogproj.navigation


sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
    object Search : Screen(route = "search_screen")
    object Detail : Screen(route = "detail_screen?stationId={stationId}"){
        fun passStationIdId(stationId: Int) =
            "detail_screen?stationId=$stationId"
    }
}

