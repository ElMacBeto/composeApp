package com.elmacbeto.composeapp.data.mapper

import com.elmacbeto.composeapp.data.datasource.local.entity.FavoriteDogEntity
import com.elmacbeto.composeapp.data.model.DogModel

class DogEntityMapper : Mapper<DogModel, FavoriteDogEntity> {
    override suspend fun map(input: DogModel): FavoriteDogEntity {
        return FavoriteDogEntity(
            id = input.id ?: 0,
            imgUrl = input.imgUrl,
            isFavorite = input.isFavorite
        )
    }
}