package com.elmacbeto.composeapp.screens.home.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.elmacbeto.composeapp.core.utils.Constants
import com.elmacbeto.composeapp.data.model.DogModel

@Composable
fun DogItem(dog: DogModel, type:Constants.ItemType, onItemSelected: () -> Unit) {
    ConstraintLayout {
        val (img, favoriteBtn) = createRefs()

        val icon = when(type){
            Constants.ItemType.HOME -> Icons.Filled.AddCircle
            Constants.ItemType.FAVORITE -> Icons.Filled.Delete
        }

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
            Icon(icon, contentDescription = "Favorite")
        }
    }
}