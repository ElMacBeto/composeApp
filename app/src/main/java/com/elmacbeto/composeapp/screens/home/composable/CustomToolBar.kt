package com.elmacbeto.composeapp.screens.home.composable

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(title:String) {
    TopAppBar(
        title = { Text(text = title) },
        modifier = Modifier.background(Color.Black),
    )
}
