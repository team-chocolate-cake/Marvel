package com.chocolatecake.marvel.data.repository

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.model.StoryResult
import com.chocolatecake.marvel.data.util.Status
import io.reactivex.rxjava3.core.Single

interface MarvelRepository {
    fun getComics(
        title: String? = null,
        limit: Int? = null,
        offset: Int? = null,
    ): Single<Status<List<ComicsResult>>>


    fun getEventByComicId(
        comicId: Int
    ): Single<Status<List<EventResult>>>

    fun getComicById(comicId: Int): Single<Status<List<ComicsResult>>>
    fun getCharactersForComic(comicId: Int): Single<Status<List<ProfileResult>>>
    fun getCharacters(
        name: String? = null,
        limit: Int? = null
    ): Single<Status<List<ProfileResult>>>

    fun getCharacterById(characterId: Int): Single<Status<List<ProfileResult>>>
    fun getComicsForCharacter(characterId: Int): Single<Status<List<ComicsResult>>>
    fun getCharacterSeries(characterId: Int): Single<Status<List<SeriesResult>>>

    fun getSeries(
        title: String? = null,
        offset: Int? = null,
        limit: Int? = null,
        orderBy: String? = null
    ): Single<Status<List<SeriesResult>>>

    fun getSeriesById(seriesId: Int): Single<Status<List<SeriesResult>>>
    fun getCharactersForSeries(seriesId: Int): Single<Status<List<ProfileResult>>>
    fun getComicsForSeries(seriesId: Int): Single<Status<List<ComicsResult>>>
    fun getEventsForSeries(seriesId: Int): Single<Status<List<EventResult>>>

    fun getCreators(
        firstName: String? = null,
        middleName: String? = null,
        lastName: String? = null
    ): Single<Status<List<ProfileResult>>>

    fun getCreatorById(creatorId: Int): Single<Status<List<ProfileResult>>>
    fun getComicsForCreator(creatorId: Int): Single<Status<List<ComicsResult>>>
    fun getSeriesForCreator(creatorId: Int): Single<Status<List<SeriesResult>>>

    fun getStories(
        limit: Int? = null,
        offset: Int? = null
    ): Single<Status<List<StoryResult>>>

    fun getStoryById(storyId: Int): Single<Status<List<StoryResult>>>
    fun getCreatorsByStoryId(storyId: Int): Single<Status<List<ProfileResult>>>
    fun getComicsByStoryId(storyId: Int): Single<Status<List<ComicsResult>>>
    fun getSeriesByStoryId(storyId: Int): Single<Status<List<SeriesResult>>>


    fun getEvents(
        limit: Int? = null,
        offset: Int? = null
    ): Single<Status<List<EventResult>>>

    fun getCharactersByEventId(eventId: Int): Single<Status<List<ProfileResult>>>

    fun getSeriesByEventId(eventId: Int): Single<Status<List<SeriesResult>>>

    fun getComicsByEventId(eventId: Int): Single<Status<List<ComicsResult>>>

    fun getSpecificEventByEventId(eventId: Int): Single<Status<List<EventResult>>>

}