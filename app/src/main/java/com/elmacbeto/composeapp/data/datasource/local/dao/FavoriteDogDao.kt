package com.elmacbeto.composeapp.data.datasource.local.dao

import androidx.room.*
import com.elmacbeto.composeapp.core.utils.Constants
import com.elmacbeto.composeapp.data.datasource.local.entity.FavoriteDogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDogDao {

    @Query("SELECT * from ${Constants.TABLE_DOG}")
    fun getFavorites(): Flow<List<FavoriteDogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(favorite: FavoriteDogEntity)

    @Update
    suspend fun updateTask(item: FavoriteDogEntity)

    @Delete
    suspend fun deleteFavorite(item: FavoriteDogEntity)

}