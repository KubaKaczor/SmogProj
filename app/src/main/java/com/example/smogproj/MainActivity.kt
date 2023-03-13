package com.example.smogproj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.smogproj.domain.repository.SmogRepository
import com.example.smogproj.navigation.Screen
import com.example.smogproj.ui.theme.SmogProjTheme
import com.example.smogproj.util.Resource
import com.example.smogproj.navigation.SetupNavGraph
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: SmogRepository

    var keepSplashOpened = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition{
            keepSplashOpened
        }
        setContent {
            SmogProjTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SetupNavGraph(startDestination = Screen.Home.route)
                }
            }
        }

        getStations(
            scope = lifecycleScope,
            repository = repository
        ){
            keepSplashOpened = false
        }
    }
}

private fun getStations(
    scope: CoroutineScope,
    repository: SmogRepository,
    onDataLoaded: () -> Unit
) {
    scope.launch(Dispatchers.IO) {
        repository.getAllStationsAndSaveToDb()
            .collect { result ->
                when(result){
                    is Resource.Success ->{
                        onDataLoaded()
                    }
                    else -> {}
                }
            }

    }
}
