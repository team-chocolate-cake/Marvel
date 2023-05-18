package com.chocolatecake.marvel.data.repository

import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.data.remote.model.dto.EventDto
import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.data.remote.model.dto.SeriesDto
import com.chocolatecake.marvel.data.remote.model.dto.StoryDto
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.domain.model.Story
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface MarvelRepository {

    /// region comics
    fun getComics(
        title: String? = null,
        limit: Int? = null,
        offset: Int? = null,
    ): Single<Status<List<ComicDto>>>

    fun getEventByComicId(comicId: Int): Single<Status<List<EventDto>>>

    fun getComicById(comicId: Int): Single<Status<List<ComicDto>>>

    fun getCharactersForComic(comicId: Int): Single<Status<List<ProfileDto>>>
    /// endregion


    /// region characters
    fun getCharacters(
        name: String? = null,
        limit: Int? = null
    ): Single<Status<List<ProfileDto>>>

    fun getCharacterById(characterId: Int): Single<Status<List<ProfileDto>>>

    fun getComicsForCharacter(characterId: Int): Single<Status<List<ComicDto>>>

    fun getCharacterSeries(characterId: Int): Single<Status<List<SeriesDto>>>
    /// endregion


    /// region creators
    fun getCreators(
        firstName: String? = null,
        middleName: String? = null,
        lastName: String? = null
    ): Single<Status<List<ProfileDto>>>

    fun getCreatorById(creatorId: Int): Single<Status<List<ProfileDto>>>

    fun getComicsForCreator(creatorId: Int): Single<Status<List<ComicDto>>>

    fun getSeriesForCreator(creatorId: Int): Single<Status<List<SeriesDto>>>
    /// endregion


    /// region series
    fun getSeries(
        title: String? = null,
        offset: Int? = null,
        limit: Int? = null,
        orderBy: String? = null
    ): Single<Status<List<SeriesDto>>>

    fun getSeriesById(seriesId: Int): Single<Status<List<SeriesDto>>>

    fun getCharactersForSeries(seriesId: Int): Single<Status<List<ProfileDto>>>

    fun getComicsForSeries(seriesId: Int): Single<Status<List<ComicDto>>>

    fun getEventsForSeries(seriesId: Int): Single<Status<List<EventDto>>>
    /// endregion


    /// region stories
    fun getStories(
        limit: Int? = null,
        offset: Int? = null
    ): Observable<Status<List<Story>>>

    fun getStoryById(storyId: Int): Single<Status<List<StoryDto>>>

    fun refreshStories():Completable

    fun getCreatorsByStoryId(storyId: Int): Single<Status<List<ProfileDto>>>

    fun getComicsByStoryId(storyId: Int): Single<Status<List<ComicDto>>>

    fun getSeriesByStoryId(storyId: Int): Single<Status<List<SeriesDto>>>
    /// endregion


    /// region events
    fun getEvents(
        limit: Int? = null,
        offset: Int? = null
    ): Single<Status<List<EventDto>>>

    fun getCharactersByEventId(eventId: Int): Single<Status<List<ProfileDto>>>

    fun getSeriesByEventId(eventId: Int): Single<Status<List<SeriesDto>>>

    fun getComicsByEventId(eventId: Int): Single<Status<List<ComicDto>>>

    fun getSpecificEventByEventId(eventId: Int): Single<Status<List<EventDto>>>
    /// endregion
}