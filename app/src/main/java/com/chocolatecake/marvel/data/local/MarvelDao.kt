package com.chocolatecake.marvel.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolatecake.marvel.data.local.entities.CharacterEntity
import com.chocolatecake.marvel.data.local.entities.ComicsEntity
import com.chocolatecake.marvel.data.local.entities.EventEntity
import com.chocolatecake.marvel.data.local.entities.SeriesEntity
import com.chocolatecake.marvel.data.local.entities.StoryEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface MarvelDao {

    /// region characters
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(characters: List<CharacterEntity>): Completable

    @Query("DELETE FROM CharacterEntity")
    fun deleteAllCharacters(): Completable

    @Query("SELECT * FROM CharacterEntity")
    fun getAllCharacters(): Single<List<CharacterEntity>>

    @Query("SELECT * FROM CharacterEntity WHERE name LIKE :characterName limit(:limit)")
    fun getFilteredCharacters(characterName: String, limit: Int = 5): Observable<List<CharacterEntity>>
    /// endregion


    /// region comics
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComics(comics: List<ComicsEntity>): Completable

    @Query("DELETE FROM TABLE_COMIC")
    fun deleteAllComics(): Completable

    @Query("SELECT * FROM TABLE_COMIC ORDER BY random() limit(:limit)")
    fun getComicsWithLimit(limit: Int = 10): Observable<List<ComicsEntity>>

    @Query("SELECT * FROM TABLE_COMIC WHERE title LIKE :comicTitle limit(:limit)")
    fun getFilteredComics(comicTitle: String, limit: Int = 5): Observable<List<ComicsEntity>>
    /// endregion


    /// region events
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvent(events: List<EventEntity>): Completable

    @Query("DELETE FROM TABLE_EVENT")
    fun deleteAllEvents(): Completable

    @Query("SELECT * FROM TABLE_EVENT ORDER BY random() limit(:limit)")
    fun getEventsWithLimit(limit: Int = 10): Observable<List<EventEntity>>
    /// endregion


    /// region series
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeries(series: List<SeriesEntity>): Completable

    @Query("select * from TABLE_SERIES order by random() limit(:limit)")
    fun getSeriesWithLimit(limit: Int = 10): Observable<List<SeriesEntity>>

    @Query("delete from TABLE_SERIES")
    fun deleteAllSeries(): Completable

    @Query("SELECT * FROM TABLE_SERIES WHERE title LIKE :seriesTitle limit(:limit)")
    fun getFilteredSeries(seriesTitle: String, limit: Int = 5): Observable<List<SeriesEntity>>
    /// endregion


    /// region story
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStories(stories: List<StoryEntity>): Completable

    @Query("DELETE FROM STORY_TABLE")
    fun deleteAllStories(): Completable

    @Query("SELECT * FROM STORY_TABLE")
    fun getAllStories(): Observable<List<StoryEntity>>
    /// endregion

}