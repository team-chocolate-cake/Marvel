package com.chocolatecake.marvel.di

import android.content.Context
import androidx.room.Room
import com.chocolatecake.marvel.data.local.MarvelDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideMarvelDataBase(
        @ApplicationContext appContext: Context,
    ): MarvelDataBase {
        return Room.databaseBuilder(
            appContext, MarvelDataBase::class.java,
            "MarvelDataBase"
        ).build()
    }

}