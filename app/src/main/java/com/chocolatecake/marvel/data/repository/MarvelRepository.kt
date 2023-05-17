package com.chocolatecake.marvel.data.repository

import com.chocolatecake.marvel.data.remote.model.ComicDto
import com.chocolatecake.marvel.data.remote.model.EventDto
import com.chocolatecake.marvel.data.remote.model.ProfileDto
import com.chocolatecake.marvel.data.remote.model.SeriesDto
import com.chocolatecake.marvel.data.remote.model.StoryDto
import com.chocolatecake.marvel.data.util.Status
import io.reactivex.rxjava3.core.Single

interface MarvelRepository {
    fun getComics(
        title: String? = null,
        limit: Int? = null,
        offset: Int? = null,
    ): Single<Status<List<ComicDto>>>


    fun getEventByComicId(
        comicId: Int
    ): Single<Status<List<EventDto>>>

    fun getComicById(comicId: Int): Single<Status<List<ComicDto>>>
    fun getCharactersForComic(comicId: Int): Single<Status<List<ProfileDto>>>
    fun getCharacters(
        name: String? = null,
        limit: Int? = null
    ): Single<Status<List<ProfileDto>>>

    fun getCharacterById(characterId: Int): Single<Status<List<ProfileDto>>>
    fun getComicsForCharacter(characterId: Int): Single<Status<List<ComicDto>>>
    fun getCharacterSeries(characterId: Int): Single<Status<List<SeriesDto>>>

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

    fun getCreators(
        firstName: String? = null,
        middleName: String? = null,
        lastName: String? = null
    ): Single<Status<List<ProfileDto>>>

    fun getCreatorById(creatorId: Int): Single<Status<List<ProfileDto>>>
    fun getComicsForCreator(creatorId: Int): Single<Status<List<ComicDto>>>
    fun getSeriesForCreator(creatorId: Int): Single<Status<List<SeriesDto>>>

    fun getStories(
        limit: Int? = null,
        offset: Int? = null
    ): Single<Status<List<StoryDto>>>

    fun getStoryById(storyId: Int): Single<Status<List<StoryDto>>>
    fun getCreatorsByStoryId(storyId: Int): Single<Status<List<ProfileDto>>>
    fun getComicsByStoryId(storyId: Int): Single<Status<List<ComicDto>>>
    fun getSeriesByStoryId(storyId: Int): Single<Status<List<SeriesDto>>>


    fun getEvents(
        limit: Int? = null,
        offset: Int? = null
    ): Single<Status<List<EventDto>>>

    fun getCharactersByEventId(eventId: Int): Single<Status<List<ProfileDto>>>

    fun getSeriesByEventId(eventId: Int): Single<Status<List<SeriesDto>>>

    fun getComicsByEventId(eventId: Int): Single<Status<List<ComicDto>>>

    fun getSpecificEventByEventId(eventId: Int): Single<Status<List<EventDto>>>

}