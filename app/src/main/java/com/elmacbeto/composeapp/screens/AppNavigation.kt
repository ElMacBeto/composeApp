package com.elmacbeto.composeapp.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elmacbeto.composeapp.data.model.Routes
import com.elmacbeto.composeapp.screens.deteailScreen.DetailScreen
import com.elmacbeto.composeapp.screens.favorites.FavoritesScreen
import com.elmacbeto.composeapp.screens.home.HomeScreen
import com.elmacbeto.composeapp.screens.home.HomeViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation(homeViewModel: HomeViewModel? = null) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {
        NavHost(navController = navController, startDestination = Routes.Home.route) {
            composable(route = Routes.Home.route) {
                HomeScreen(homeViewModel, navController){
                    scope.launch {
                        snackbarHostState.showSnackbar(it)
                    }
                }
            }
            composable(
                route = Routes.DogDetail.route,
                arguments = Routes.DogDetail.navArguments
            ) {
                FavoritesScreen(navController)
            }
            composable(
                route = Routes.Favorites.route,
                arguments = Routes.Favorites.navArguments
            ) {
                DetailScreen(navController)
            }
        }
    }
}


@Preview
@Composable
fun PreviewApp() {
    Surface {
        AppNavigation()
    }

}