package com.chocolatecake.marvel.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolatecake.marvel.data.local.entities.StoryEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface StoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStories(stories: List<StoryEntity>): Completable

    @Query("DELETE FROM STORY_TABLE")
    fun deleteAllStories(): Completable

    @Query("SELECT * FROM STORY_TABLE ORDER BY random()")
    fun getAllStories(): Observable<List<StoryEntity>>
}