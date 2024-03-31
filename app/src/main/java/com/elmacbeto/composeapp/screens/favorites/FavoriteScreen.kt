package com.elmacbeto.composeapp.screens.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.elmacbeto.composeapp.core.utils.Constants
import com.elmacbeto.composeapp.core.utils.Status
import com.elmacbeto.composeapp.data.datasource.Resource
import com.elmacbeto.composeapp.data.model.DogModel
import com.elmacbeto.composeapp.screens.home.composable.DogItem
import com.elmacbeto.composeapp.screens.home.composable.Toolbar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoritesScreen(
    favoriteViewModel: FavoriteViewModel?,
    navController: NavController? = null,
    onError: ((String) -> Unit)? = null
) {
    Scaffold(
        topBar = { Toolbar("Favorite") },
        content = { FavoritesContent(favoriteViewModel, onError) },
    )

}

@Composable
fun FavoritesContent(
    favoriteViewModel: FavoriteViewModel?,
    onError: ((String) -> Unit)? = null
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val uiState by produceState<Resource<List<DogModel>>>(
        initialValue = Resource.loading(),
        key1 = lifecycle,
        key2 = favoriteViewModel
    ) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            favoriteViewModel?.uiState?.collect { value = it }
        }
    }

    when (uiState.status) {
        Status.ERROR -> {
            onError?.invoke(uiState.message.toString())
        }

        Status.LOADING -> {
            CircularProgressIndicator()
        }

        Status.SUCCESS -> {
            Box(modifier = Modifier.fillMaxSize()) {
                val dogList: List<DogModel> = uiState.data ?: emptyList()
                FavoriteList(dogList, favoriteViewModel)
            }
        }
    }
}

@Composable
fun FavoriteList(dogList: List<DogModel>, favoriteViewModel: FavoriteViewModel?) {
    if (dogList.isEmpty()){
        Text(text = "Lista vacia")
        return
    }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(dogList) { dog ->
            DogItem(dog, Constants.ItemType.FAVORITE) { favoriteViewModel?.deleteFavorite(dog) }
        }
    }
}

@Preview
@Composable
fun PreviewFavoriteScreen() {
    Surface {
        FavoritesScreen(null)
    }

}