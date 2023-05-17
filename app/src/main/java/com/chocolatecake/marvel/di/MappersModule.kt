package com.chocolatecake.marvel.di

import com.chocolatecake.marvel.domain.mapper.character.CharacterMapper
import com.chocolatecake.marvel.domain.mapper.character.CharacterUIMapper
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

    /// endregion


    /// region story

    /// endregion
}