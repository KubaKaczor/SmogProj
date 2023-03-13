package com.example.smogproj.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import coil.ImageLoader
import com.example.smogproj.presentation.detail.DetailScreen
import com.example.smogproj.presentation.home.HomeScreen
import com.example.smogproj.presentation.search.SearchScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.animation.composable
import com.ramcosta.composedestinations.spec.DestinationStyle.Animated.None.exitTransition

@OptIn(ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun SetupNavGraph(
    startDestination: String,
) {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        startDestination = startDestination,
        navController = navController
    ) {
        homeRoute(
            navigateToSearch = {
                navController.navigate(Screen.Search.route)
            },
            navigateToDetail = {
                navController.navigate(Screen.Detail.passStationIdId(it))
            }
        )
        searchRoute(
            navigateToBack = {
                navController.popBackStack()
            },
            navigateToDetail = {
                navController.navigate(Screen.Detail.passStationIdId(it))
            }
        )

        detailRoute(
            navigateToBack = {
                navController.popBackStack()
            }
        )

    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeRoute(
    navigateToSearch: () -> Unit,
    navigateToDetail: (Int) -> Unit
) {
    composable(
        route = Screen.Home.route,
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(300))
        },
    ) {
        HomeScreen(
            onSearchClick = navigateToSearch,
            onDetailClick = {navigateToDetail(it)}
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.searchRoute(
    navigateToBack: () -> Unit,
    navigateToDetail: (Int) -> Unit
) {
    composable(
        route = Screen.Search.route,
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { 300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { 300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(300))
        },
    ) {
        SearchScreen(
            onBackClick = navigateToBack,
            onDetailClick = { navigateToDetail(it)}
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.detailRoute(
    navigateToBack: () -> Unit,
) {
    composable(
        route = Screen.Detail.route,
        arguments = listOf(navArgument(name = "stationId") {
            type = NavType.IntType
        }),
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { 300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { 300 },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ) + fadeOut(animationSpec = tween(300))
        }
    ) {
        DetailScreen(
            onBackClick = navigateToBack
        )
    }
}



