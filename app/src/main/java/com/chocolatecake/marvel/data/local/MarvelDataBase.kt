package com.chocolatecake.marvel.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chocolatecake.marvel.data.local.dao.CharacterDao
import com.chocolatecake.marvel.data.local.dao.ComicDao
import com.chocolatecake.marvel.data.local.dao.EventDao
import com.chocolatecake.marvel.data.local.dao.SeriesDao
import com.chocolatecake.marvel.data.local.dao.StoryDao
import com.chocolatecake.marvel.data.local.entities.CharacterEntity
import com.chocolatecake.marvel.data.local.entities.ComicsEntity
import com.chocolatecake.marvel.data.local.entities.EventEntity
import com.chocolatecake.marvel.data.local.entities.SeriesEntity
import com.chocolatecake.marvel.data.local.entities.StoryEntity

@Database(
    entities = [
        CharacterEntity::class,
        ComicsEntity::class,
        EventEntity::class,
        SeriesEntity::class,
        StoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MarvelDataBase : RoomDatabase() {
    abstract val characterDao: CharacterDao
    abstract val seriesDao: SeriesDao
    abstract val eventDao: EventDao
    abstract val comicDao: ComicDao
    abstract val storyDao:StoryDao
}