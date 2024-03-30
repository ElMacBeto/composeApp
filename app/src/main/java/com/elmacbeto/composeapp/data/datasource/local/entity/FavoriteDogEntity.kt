package com.elmacbeto.composeapp.data.datasource.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elmacbeto.composeapp.core.utils.Constants
import kotlinx.parcelize.Parcelize


@Entity(tableName = Constants.TABLE_DOG)
data class FavoriteDogEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val imgUrl: String = "",
    var isFavorite: Boolean = false
)