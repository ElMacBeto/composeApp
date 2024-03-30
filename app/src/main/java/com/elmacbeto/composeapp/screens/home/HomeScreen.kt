package com.elmacbeto.composeapp.screens.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.elmacbeto.composeapp.core.utils.Status
import com.elmacbeto.composeapp.data.datasource.Resource
import com.elmacbeto.composeapp.data.model.DogModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel? = null,
    navController: NavController? = null,
    onError: ((String) -> Unit)? = null
) {

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

    when(uiState.status){
        Status.ERROR -> { onError?.invoke(uiState.message.toString()) }
        Status.LOADING -> { CircularProgressIndicator() }
        Status.SUCCESS -> {
            Box(modifier = Modifier.fillMaxSize()) {
                DogsList(uiState.data ?: emptyList(), homeViewModel)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogsList(dogList: List<DogModel>, homeViewModel: HomeViewModel?) {
    ConstraintLayout {
        val (search) = createRefs()
        val searchText: String by homeViewModel?.searchText!!.observeAsState("")

        SearchBar(
            query = searchText,
            onQueryChange = { homeViewModel?.onSearchTextChange(it) },
            onSearch = { homeViewModel?.onSummit(it) },
            active = true,
            onActiveChange = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(search) {
                    top.linkTo(parent.top, margin = 16.dp)
                }
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                items(dogList) { dog ->
                    DogItem(dog){
                        homeViewModel?.saveFavorite(dog)
                    }
                }
            }
        }
    }

}

@Composable
fun DogItem(dog: DogModel, onItemSelected: () -> Unit) {
    ConstraintLayout {
        val (img, favoriteBtn) = createRefs()
        Image(
            painter = rememberAsyncImagePainter(dog.imgUrl),
            contentScale = ContentScale.FillBounds,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .clip(RoundedCornerShape(16.dp))
                .constrainAs(img) {
                    top.linkTo(parent.top, margin = 16.dp)
                }
        )
        IconButton(onClick = { onItemSelected.invoke() },
            modifier = Modifier.constrainAs(favoriteBtn) {
                top.linkTo(img.top, margin = 16.dp)
                end.linkTo(img.end, margin = 16.dp)
            }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
        }
    }
}




@Preview
@Composable
fun PreviewHomeScreen() {
    Surface {
        HomeScreen()
    }
}