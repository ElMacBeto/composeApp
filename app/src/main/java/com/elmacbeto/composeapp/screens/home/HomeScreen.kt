package com.elmacbeto.composeapp.screens.home


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.elmacbeto.composeapp.core.utils.Status
import com.elmacbeto.composeapp.data.datasource.Resource
import com.elmacbeto.composeapp.data.model.AllDogModel
import com.elmacbeto.composeapp.data.model.Routes
import com.elmacbeto.composeapp.screens.home.composable.DogsList

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel? = null,
    navController: NavController? = null,
    onError: ((String) -> Unit)? = null
) {
    Scaffold(
        content = { HomeContent(homeViewModel, onError) },
        floatingActionButton = { FAB(navController)},
        floatingActionButtonPosition = FabPosition.End
    )
}

@Composable
fun HomeContent(
    homeViewModel: HomeViewModel? = null,
    onError: ((String) -> Unit)? = null
) {

    val uiState by homeViewModel?.allDogsLiveData!!.observeAsState(Resource.success(AllDogModel()))

    when (uiState.status) {
        Status.ERROR -> {
            onError?.invoke(uiState.message.toString())
        }

        Status.LOADING -> {
            CircularProgressIndicator()
        }

        Status.SUCCESS -> {
            Box(modifier = Modifier.fillMaxSize()) {
                DogsList(uiState.data?.imgUrlList ?: emptyList(), homeViewModel)
            }
        }
    }
}

@Composable
fun FAB( navController: NavController? = null) {
    FloatingActionButton(
        onClick = { navController?.navigate(Routes.Favorites.route) },
    ) {
        Icon(Icons.Filled.Favorite, "Favorites.")
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    Surface {
        HomeScreen()
    }
}