package com.elmacbeto.composeapp.core.di

import android.content.Context
import androidx.room.Room
import com.elmacbeto.composeapp.core.utils.Constants
import com.elmacbeto.composeapp.data.datasource.local.BuildDataBase
import com.elmacbeto.composeapp.data.datasource.local.dao.FavoriteDogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun provideFavoriteDogDao(buildDataBase: BuildDataBase):FavoriteDogDao{
        return buildDataBase.factsDAO()
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext appContext: Context): BuildDataBase {
        return Room.databaseBuilder(appContext, BuildDataBase::class.java, Constants.DATABASE_NAME)
            .build()
    }

    
}