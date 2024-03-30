package com.elmacbeto.composeapp.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elmacbeto.composeapp.data.datasource.local.dao.FavoriteDogDao
import com.elmacbeto.composeapp.data.datasource.local.entity.FavoriteDogEntity

@Database(
    entities = [FavoriteDogEntity::class],
    version = 1,
    exportSchema = true
)
abstract class BuildDataBase : RoomDatabase() {
    abstract fun factsDAO(): FavoriteDogDao
}