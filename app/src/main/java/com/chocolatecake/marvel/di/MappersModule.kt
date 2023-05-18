package com.chocolatecake.marvel.di

import com.chocolatecake.marvel.domain.mapper.character.CharacterMapper
import com.chocolatecake.marvel.domain.mapper.character.CharacterUIMapper
import com.chocolatecake.marvel.domain.mapper.series.SeriesMapper
import com.chocolatecake.marvel.domain.mapper.series.SeriesUIMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MappersModule {

    /// region character
    @Provides
    @ViewModelScoped
    fun provideCharacterMapper(): CharacterMapper {
        return CharacterMapper()
    }

    @Provides
    @ViewModelScoped
    fun provideCharacterUIMapper(): CharacterUIMapper {
        return CharacterUIMapper()
    }
    /// endregion


    /// region comic

    /// endregion


    /// region event

    /// endregion


    /// region series
    @Provides
    @ViewModelScoped
    fun provideSeriesMapper(): SeriesMapper {
        return SeriesMapper()
    }

    @Provides
    @ViewModelScoped
    fun provideSeriesUIMapper(): SeriesUIMapper {
        return SeriesUIMapper()
    }
    /// endregion


    /// region story

    /// endregion
}