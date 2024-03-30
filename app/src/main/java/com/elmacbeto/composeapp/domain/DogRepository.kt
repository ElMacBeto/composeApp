package com.elmacbeto.composeapp.domain

import android.util.Log
import com.elmacbeto.composeapp.data.datasource.Resource
import com.elmacbeto.composeapp.data.datasource.local.dao.FavoriteDogDao
import com.elmacbeto.composeapp.data.datasource.local.entity.FavoriteDogEntity
import com.elmacbeto.composeapp.data.datasource.remote.DogClient
import com.elmacbeto.composeapp.data.mapper.DogEntityMapper
import com.elmacbeto.composeapp.data.mapper.DogResponseMapper
import com.elmacbeto.composeapp.data.model.AllDogModel
import com.elmacbeto.composeapp.data.model.DogModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogRepository @Inject constructor(
    private val apiService: DogClient,
    private val favoriteDao: FavoriteDogDao
) {
    suspend fun getDogByBreed(breed: String): Resource<AllDogModel> {
        return try {
            val response = apiService.getDogByBreed(breed)
            if (response.isSuccessful) {
                Resource.success(DogResponseMapper().map(response.body()!!))
            } else {
                Resource.error("Busqueda no encontrada")
            }
        } catch (e: IOException) {
            Resource.error("Error al conectar")
        }
    }

    val favorites: Flow<List<DogModel>> =
        favoriteDao.getFavorites().map { items -> items.map { DogModel(it.id, it.imgUrl, it.isFavorite) } }

    suspend fun saveFavorite(dog: DogModel) {
        favoriteDao.insertData(DogEntityMapper().map(dog))
    }

    suspend fun update(dog: DogModel){
        favoriteDao.updateTask(DogEntityMapper().map(dog))
    }

    suspend fun delete(dog: DogModel){
        favoriteDao.deleteTask(DogEntityMapper().map(dog))
    }
}