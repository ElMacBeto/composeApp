package com.elmacbeto.composeapp.screens.home.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.elmacbeto.composeapp.core.utils.Constants
import com.elmacbeto.composeapp.data.model.DogModel
import com.elmacbeto.composeapp.screens.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogsList(
    dogList: List<DogModel>,
    homeViewModel: HomeViewModel?,
) {
    ConstraintLayout {
        val (search) = createRefs()
        val searchText: String by homeViewModel?.searchText!!.observeAsState("Buscar")

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
                    bottom.linkTo(parent.bottom, margin = 16.dp)
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
                    DogItem(dog, Constants.ItemType.HOME) {
                        homeViewModel?.saveFavorite(dog)
                    }
                }
            }
        }
    }
}