package com.elmacbeto.composeapp.data.model

import androidx.navigation.NamedNavArgument

sealed class Routes(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    object Home : Routes("home")

    object DogDetail : Routes("dogDetail/{breed}"){
        fun createRoute(breed: String) = "dogDetail/$breed"
    }

    object Favorites : Routes("favorites")

}