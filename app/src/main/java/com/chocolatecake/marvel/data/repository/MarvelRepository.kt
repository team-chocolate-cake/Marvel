package com.chocolatecake.marvel.data.repository

import android.app.appsearch.AppSearchResult
import android.provider.ContactsContract
import com.chocolatecake.marvel.data.local.entities.SearchHistoryEntity
import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.data.remote.model.dto.EventDto
import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.data.remote.model.dto.SeriesDto
import com.chocolatecake.marvel.data.remote.model.dto.StoryDto
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.domain.model.Character
import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.domain.model.ComicDetails
import com.chocolatecake.marvel.domain.model.CreatorDetails
import com.chocolatecake.marvel.domain.model.Event
import com.chocolatecake.marvel.domain.model.EventDetails
import com.chocolatecake.marvel.domain.model.SearchHistory
import com.chocolatecake.marvel.domain.model.Series
import com.chocolatecake.marvel.domain.model.SeriesDetails
import com.chocolatecake.marvel.domain.model.Story
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface MarvelRepository {

    /// region comics
    fun getComics(
        title: String? = null,
        limit: Int = 10,
    ): Observable<Status<List<Comic>>>

    fun searchComics(
        title: String = "",
        limit: Int
    ): Observable<Status<List<Comic>>>

    fun refreshComics(
        title: String? = null,
        limit: Int? = null,
        offset: Int? = null,
    ): Completable

    fun getEventByComicId(comicId: Int): Single<Status<List<Event>>>

    fun getComicById(comicId: Int): Single<Status<ComicDetails>>

    fun getCharactersForComic(comicId: Int): Single<Status<List<ProfileDto>>>
    /// endregion


    /// region characters
    fun refreshCharacters(
        name: String? = null,
        limit: Int? = null
    ): Completable

    fun getCharacters(
        name: String = "",
        limit: Int? = null
    ): Observable<Status<List<Character>>>

    fun getCharacterById(characterId: Int): Single<Status<List<ProfileDto>>>

    fun getComicsForCharacter(characterId: Int): Single<Status<List<ComicDto>>>

    fun getCharacterSeries(characterId: Int): Single<Status<List<SeriesDto>>>
    /// endregion


    /// region creators
    fun getCreatorById(creatorId: Int): Single<Status<List<CreatorDetails>>>

    fun getComicsForCreator(creatorId: Int): Single<Status<List<Comic>>>

    fun getSeriesForCreator(creatorId: Int): Single<Status<List<Series>>>
    /// endregion


    /// region series
    fun getSeries(
        limit: Int = 10,
    ): Observable<Status<List<Series>>>

    fun searchSeries(
        title: String = "",
        limit: Int,
    ): Observable<Status<List<Series>>>

    fun refreshSeries(
        title: String? = null,
        limit: Int,
        offset: Int = 0,
    ): Completable

    fun getSeriesById(seriesId: Int): Single<Status<SeriesDetails>>

    fun getCharactersForSeries(seriesId: Int): Single<Status<List<Character>>>

    fun getComicsForSeries(seriesId: Int): Single<Status<List<Comic>>>

    fun getEventsForSeries(seriesId: Int): Single<Status<List<Event>>>
    /// endregion


    /// region stories
    fun getStories(
        limit: Int? = null,
        offset: Int? = null
    ): Observable<Status<List<Story>>>

    fun refreshStories(limit: Int? = null, offset: Int? = null): Completable

    fun getStoryById(storyId: Int): Single<Status<List<StoryDto>>>

    fun getCreatorsByStoryId(storyId: Int): Single<Status<List<ProfileDto>>>

    fun getComicsByStoryId(storyId: Int): Single<Status<List<ComicDto>>>

    fun getSeriesByStoryId(storyId: Int): Single<Status<List<SeriesDto>>>
    /// endregion


    /// region events
    fun getEvents(
        limit: Int = 10,
        offset: Int? = null
    ): Observable<Status<List<Event>>>

    fun refreshEvent(
        limit: Int? = null,
        offset: Int? = null
    ): Completable

    fun getCharactersByEventId(eventId: Int): Single<Status<List<Character>>>

    fun getSeriesByEventId(eventId: Int): Single<Status<List<Series>>>

    fun getComicsByEventId(eventId: Int): Single<Status<List<Comic>>>

    fun getSpecificEventByEventId(eventId: Int): Single<Status<EventDetails>>
    /// endregion


    /// region search history
    fun getFilteredSearchHistory(keyword: String , type: String): Observable<List<SearchHistory>>

    fun insertSearchHistory(searchHistory: SearchHistory): Completable

    fun deleteSearchHistory(searchHistory: SearchHistory): Completable
    //endregion
}