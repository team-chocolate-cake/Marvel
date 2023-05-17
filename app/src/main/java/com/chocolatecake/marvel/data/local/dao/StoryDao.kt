package com.chocolatecake.marvel.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolatecake.marvel.data.local.entities.StoryEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface StoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSories(stories: List<StoryEntity>): Completable

    @Query("DELETE FROM StoryEntity")
    fun deleteAllStories(): Completable

    @Query("SELECT * FROM StoryEntity")
    fun getAllStories(): Single<List<StoryEntity>>

}