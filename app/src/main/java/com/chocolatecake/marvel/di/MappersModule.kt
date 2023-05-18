package com.chocolatecake.marvel.di

import com.chocolatecake.marvel.domain.mapper.character.CharacterMapper
import com.chocolatecake.marvel.domain.mapper.character.CharacterUIMapper
import com.chocolatecake.marvel.domain.mapper.story.StoryMapper
import com.chocolatecake.marvel.domain.mapper.story.StoryUIMapper
import com.chocolatecake.marvel.domain.mapper.comic.ComicMapper
import com.chocolatecake.marvel.domain.mapper.comic.ComicUIMapper
import com.chocolatecake.marvel.domain.mapper.event.EventMapper
import com.chocolatecake.marvel.domain.mapper.event.EventUIMapper
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
    @Provides
    @ViewModelScoped
    fun provideComicMapper(): ComicMapper{
        return ComicMapper()
    }

    @Provides
    @ViewModelScoped
    fun provideComicUIMapper(): ComicUIMapper{
        return ComicUIMapper()
    }
    /// endregion


    /// region event
    @Provides
    @ViewModelScoped
    fun provideEventMapper(): EventMapper{
        return EventMapper()
    }

    @Provides
    @ViewModelScoped
    fun provideEventUIUIMapper(): EventUIMapper {
        return EventUIMapper()
    }
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
    @Provides
    @ViewModelScoped
    fun provideStoryMapper(): StoryMapper {
        return StoryMapper()
    }

    @Provides
    @ViewModelScoped
    fun provideStoryUIMapper(): StoryUIMapper {
        return StoryUIMapper()
    }
    /// endregion
}