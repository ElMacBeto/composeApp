package com.elmacbeto.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.elmacbeto.composeapp.screens.AppNavigation
import com.elmacbeto.composeapp.screens.favorites.FavoriteViewModel
import com.elmacbeto.composeapp.screens.home.HomeViewModel
import com.elmacbeto.composeapp.ui.theme.ComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //homeViewModel.getDogByBreed("chihuahua")

        setContent {
            ComposeAppTheme {
                // A surface container using the 'background' color from the theme
                AppNavigation(homeViewModel, favoriteViewModel)
            }
        }
    }
}


