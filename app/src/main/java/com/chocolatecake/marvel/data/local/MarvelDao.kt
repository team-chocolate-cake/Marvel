package com.chocolatecake.marvel.data.local

import android.app.appsearch.AppSearchResult
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolatecake.marvel.data.local.entities.CharacterEntity
import com.chocolatecake.marvel.data.local.entities.ComicsEntity
import com.chocolatecake.marvel.data.local.entities.EventEntity
import com.chocolatecake.marvel.data.local.entities.SearchHistoryEntity
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
    /// endregion

    /// region comics

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComics(comics: List<ComicsEntity>): Completable

    @Query("DELETE FROM TABLE_COMIC")
    fun deleteAllComics(): Completable

    @Query("SELECT * FROM TABLE_COMIC ORDER BY random() limit(:limit)")
    fun getComicsWithLimit(limit: Int = 10): Observable<List<ComicsEntity>>
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
    /// endregion

    /// region story

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStories(stories: List<StoryEntity>): Completable

    @Query("DELETE FROM STORY_TABLE")
    fun deleteAllStories(): Completable

    @Query("SELECT * FROM STORY_TABLE")
    fun getAllStories(): Observable<List<StoryEntity>>
    /// endregion


    ///region search history
    @Query("SELECT * FROM SEARCH_HISTORY_TABLE WHERE keyword LIKE :keyword AND type == :type ORDER BY ID DESC")
    fun getFilteredSearchHistory(keyword: String , type: String): Single<List<SearchHistoryEntity>>

    @Insert
    fun insertSearchHistory(searchResult: SearchHistoryEntity): Completable

    @Delete
    fun deleteSearchHistory(search: SearchHistoryEntity): Completable

    @Query("SELECT * FROM SEARCH_HISTORY_TABLE WHERE type == :type ORDER BY ID DESC")
    fun getAllSearchHistory(type: String): Single<List<SearchHistoryEntity>>
    ///endregion

}