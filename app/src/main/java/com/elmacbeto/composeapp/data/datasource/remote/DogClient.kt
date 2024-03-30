package com.elmacbeto.composeapp.data.datasource.remote

import com.elmacbeto.composeapp.data.datasource.remote.response.AllDogsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogClient {

    @GET("/all/{breed}/images")
    suspend fun getRandomDog(@Path("breed") breed: String): Response<AllDogsResponse>

    @GET("{breed}/images")
    suspend fun getDogByBreed(@Path("breed") breed: String): Response<AllDogsResponse>

}