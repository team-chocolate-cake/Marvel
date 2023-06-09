package com.chocolatecake.marvel.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chocolatecake.marvel.data.local.entities.CharacterEntity
import com.chocolatecake.marvel.data.local.entities.ComicsEntity
import com.chocolatecake.marvel.data.local.entities.EventEntity
import com.chocolatecake.marvel.data.local.entities.SearchHistoryEntity
import com.chocolatecake.marvel.data.local.entities.SeriesEntity
import com.chocolatecake.marvel.data.local.entities.StoryEntity

@Database(
    entities = [
        CharacterEntity::class,
        ComicsEntity::class,
        EventEntity::class,
        SeriesEntity::class,
        StoryEntity::class,
        SearchHistoryEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class MarvelDataBase : RoomDatabase() {
    abstract val marvelDao: MarvelDao
}