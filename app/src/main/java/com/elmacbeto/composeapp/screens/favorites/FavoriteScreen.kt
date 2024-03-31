package com.elmacbeto.composeapp.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController


@Composable
fun FavoritesScreen(navController: NavController? = null) {

    /*
       val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uiState by produceState<Resource<List<DogModel>>>(
        initialValue = Resource.loading(),
        key1 = lifecycle,
        key2 = homeViewModel
    ){
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED){
            homeViewModel?.uiState?.collect{ value = it}
        }
    }

     */

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.Cyan),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Ejemplo 1")
        }

    }
}

@Preview
@Composable
fun PreviewFavoriteScreen() {
    Surface {
        FavoritesScreen()
    }

}