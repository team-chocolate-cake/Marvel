package com.chocolatecake.marvel.data.remote.service

import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult
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
        @Query("offset")  offset: Int? = null
    ): Single<Response<BaseResponse<ComicsResult>>>

    @GET("comics/{comicId}/events")
    fun getEventByComicId(
        @Path("comicId") comicId: Int
    ): Single<Response<BaseResponse<EventResult>>>

    @GET("comics/{comicId}")
    fun getComicById(
        @Path("comicId") comicId: Int,
    ): Single<Response<BaseResponse<ComicsResult>>>

    @GET("comics/{comicId}/characters")
    fun getCharactersForComic(
        @Path("comicId") comicId: Int,
    ): Single<Response<BaseResponse<ProfileResult>>>

    @GET("characters")
    fun getCharacters(
        @Query("nameStartsWith") name: String? = null,
        @Query("limit") limit: Int? = null
    ): Single<Response<BaseResponse<ProfileResult>>>

    @GET("characters/{characterId}")
    fun getCharacterById(
        @Path("characterId") characterId: Int,
    ): Single<Response<BaseResponse<ProfileResult>>>

    @GET("characters/{characterId}/comics")
    fun getComicsForCharacter(
        @Path("characterId") characterId: Int,
    ): Single<Response<BaseResponse<ComicsResult>>>

    @GET("characters/{characterId}/series")
    fun getSeriesForCharacter(
        @Path("characterId") characterId: Int,
    ): Single<Response<BaseResponse<SeriesResult>>>

    @GET("creators")
    fun getCreators(
        @Query("firstName") firstName: String? = null,
        @Query("middleName") middleName: String? = null,
        @Query("lastName") lastName: String? = null
    ): Single<Response<BaseResponse<ProfileResult>>>

    @GET("creators/{creatorId}")
    fun getCreatorById(
        @Path("creatorId") creatorId: Int,
    ): Single<Response<BaseResponse<ComicsResult>>>

    @GET("creators/{creatorId}/comics")
    fun getComicsForCreator(
        @Path("creatorId") creatorId: Int,
    ): Single<Response<BaseResponse<ComicsResult>>>

    @GET("creators/{creatorId}/series")
    fun getSeriesForCreator(
        @Path("creatorId") creatorId: Int,
    ): Single<Response<BaseResponse<SeriesResult>>>

    @GET("series")
    fun getSeries(
        @Query("titleStartsWith") title: String? = null,
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null
    ): Single<Response<BaseResponse<SeriesResult>>>

    @GET("series/{seriesId}")
    fun getSeriesById(
        @Path("seriesId") seriesId: Int,
    ): Single<Response<BaseResponse<SeriesResult>>>

    @GET("series/{seriesId}/characters")
    fun getCharactersForSeries(
        @Path("seriesId") seriesId: Int,
    ): Single<Response<BaseResponse<ProfileResult>>>

    @GET("series/{seriesId}/comics")
    fun getComicsForSeries(
        @Path("seriesId") seriesId: Int,
    ): Single<Response<BaseResponse<ComicsResult>>>

    @GET("series/{seriesId}/events")
    fun getEventsForSeries(
        @Path("seriesId") seriesId: Int,
    ): Single<Response<BaseResponse<EventResult>>>


    @GET("events")
    fun getEvents(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Single<Response<BaseResponse<EventResult>>>

    @GET("events/{eventId}")
    fun getEventById(
        @Path("eventId") storyId: Int,
    ): Single<Response<BaseResponse<EventResult>>>

    @GET("events/{eventId}/characters")
    fun getCharactersForEvent(
        @Path("eventId") storyId: Int,
    ): Single<Response<BaseResponse<EventResult>>>

    @GET("events/{eventId}/comics")
    fun getComicsForEvent(
        @Path("eventId") storyId: Int,
    ): Single<Response<BaseResponse<EventResult>>>


    @GET("events/{eventId}/characters")
    fun getCharactersByEventId(
        @Path("eventId") eventId: Int,
    ): Single<Response<BaseResponse<ProfileResult>>>

    @GET("events/{eventId}/series")
    fun getSeriesByEventId(
        @Path("eventId") eventId: Int,
    ): Single<Response<BaseResponse<SeriesResult>>>

    @GET("events/{eventId}/comics")
    fun getComicsByEventId(
        @Path("eventId") eventId: Int,
    ): Single<Response<BaseResponse<ComicsResult>>>

    @GET("events/{eventId}")
    fun getSpecificEventByEventId(
        @Path("eventId") eventId: Int,
    ): Single<Response<BaseResponse<EventResult>>>

}