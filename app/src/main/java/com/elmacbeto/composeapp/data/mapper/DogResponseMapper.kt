package com.elmacbeto.composeapp.data.mapper

import com.elmacbeto.composeapp.data.datasource.remote.response.AllDogsResponse
import com.elmacbeto.composeapp.data.model.AllDogModel
import com.elmacbeto.composeapp.data.model.DogModel

class DogResponseMapper : Mapper<AllDogsResponse, AllDogModel> {
    override suspend fun map(input: AllDogsResponse): AllDogModel {
        return AllDogModel(
           imgUrlList = input.images.map {
               DogModel(imgUrl = it)
           }
        )
    }
}