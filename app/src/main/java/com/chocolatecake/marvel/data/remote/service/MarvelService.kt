package com.chocolatecake.marvel.data.remote.service

import com.chocolatecake.marvel.data.remote.model.ComicDto
import com.chocolatecake.marvel.data.remote.model.EventDto
import com.chocolatecake.marvel.data.remote.model.ProfileDto
import com.chocolatecake.marvel.data.remote.model.SeriesDto
import com.chocolatecake.marvel.data.remote.model.StoryDto
import com.chocolatecake.marvel.data.remote.model.base.BaseResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("comics")
    fun getComics(
        @Query("titleStartsWith") title: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Single<Response<BaseResponse<ComicDto>>>

    @GET("comics/{comicId}/events")
    fun getEventByComicId(
        @Path("comicId") comicId: Int
    ): Single<Response<BaseResponse<EventDto>>>

    @GET("comics/{comicId}")
    fun getComicById(
        @Path("comicId") comicId: Int,
    ): Single<Response<BaseResponse<ComicDto>>>

    @GET("comics/{comicId}/characters")
    fun getCharactersForComic(
        @Path("comicId") comicId: Int,
    ): Single<Response<BaseResponse<ProfileDto>>>

    @GET("characters")
    fun getCharacters(
        @Query("nameStartsWith") name: String? = null,
        @Query("limit") limit: Int? = null
    ): Single<Response<BaseResponse<ProfileDto>>>

    @GET("characters/{characterId}")
    fun getCharacterById(
        @Path("characterId") characterId: Int,
    ): Single<Response<BaseResponse<ProfileDto>>>

    @GET("characters/{characterId}/comics")
    fun getComicsForCharacter(
        @Path("characterId") characterId: Int,
    ): Single<Response<BaseResponse<ComicDto>>>

    @GET("characters/{characterId}/series")
    fun getSeriesForCharacter(
        @Path("characterId") characterId: Int,
    ): Single<Response<BaseResponse<SeriesDto>>>

    @GET("creators")
    fun getCreators(
        @Query("firstName") firstName: String? = null,
        @Query("middleName") middleName: String? = null,
        @Query("lastName") lastName: String? = null
    ): Single<Response<BaseResponse<ProfileDto>>>

    @GET("creators/{creatorId}")
    fun getCreatorById(
        @Path("creatorId") creatorId: Int,
    ): Single<Response<BaseResponse<ProfileDto>>>

    @GET("creators/{creatorId}/comics")
    fun getComicsForCreator(
        @Path("creatorId") creatorId: Int,
    ): Single<Response<BaseResponse<ComicDto>>>

    @GET("creators/{creatorId}/series")
    fun getSeriesForCreator(
        @Path("creatorId") creatorId: Int,
    ): Single<Response<BaseResponse<SeriesDto>>>

    @GET("series")
    fun getSeries(
        @Query("titleStartsWith") title: String? = null,
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null,
        @Query("orderBy") orderBy: String? = null
    ): Single<Response<BaseResponse<SeriesDto>>>

    @GET("series/{seriesId}")
    fun getSeriesById(
        @Path("seriesId") seriesId: Int,
    ): Single<Response<BaseResponse<SeriesDto>>>

    @GET("series/{seriesId}/characters")
    fun getCharactersForSeries(
        @Path("seriesId") seriesId: Int,
    ): Single<Response<BaseResponse<ProfileDto>>>

    @GET("series/{seriesId}/comics")
    fun getComicsForSeries(
        @Path("seriesId") seriesId: Int,
    ): Single<Response<BaseResponse<ComicDto>>>

    @GET("series/{seriesId}/events")
    fun getEventsForSeries(
        @Path("seriesId") seriesId: Int,
    ): Single<Response<BaseResponse<EventDto>>>


    @GET("events")
    fun getEvents(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Single<Response<BaseResponse<EventDto>>>


    @GET("events/{eventId}/characters")
    fun getCharactersByEventId(
        @Path("eventId") eventId: Int,
    ): Single<Response<BaseResponse<ProfileDto>>>

    @GET("events/{eventId}/series")
    fun getSeriesByEventId(
        @Path("eventId") eventId: Int,
    ): Single<Response<BaseResponse<SeriesDto>>>

    @GET("events/{eventId}/comics")
    fun getComicsByEventId(
        @Path("eventId") eventId: Int,
    ): Single<Response<BaseResponse<ComicDto>>>

    @GET("events/{eventId}")
    fun getSpecificEventByEventId(
        @Path("eventId") eventId: Int,
    ): Single<Response<BaseResponse<EventDto>>>


    @GET("stories")
    fun getStories(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Single<Response<BaseResponse<StoryDto>>>

    @GET("stories/{storyId}")
    fun getStoryById(
        @Path("storyId") storyId: Int
    ): Single<Response<BaseResponse<StoryDto>>>


    @GET("stories/{storyId}/creators")
    fun getCreatorsByStoryId(
        @Path("storyId") storyId: Int
    ): Single<Response<BaseResponse<ProfileDto>>>

    @GET("stories/{storyId}/comics")
    fun getComicsByStoryId(
        @Path("storyId") storyId: Int
    ): Single<Response<BaseResponse<ComicDto>>>

    @GET("stories/{storyId}/series")
    fun getSeriesByStoryId(
        @Path("storyId") storyId: Int
    ): Single<Response<BaseResponse<SeriesDto>>>

}