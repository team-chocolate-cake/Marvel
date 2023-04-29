package com.chocolatecake.marvel.data.remote.service

import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.model.comics.ComicsResult
import com.chocolatecake.marvel.data.model.creators.CreatorsResult
import com.chocolatecake.marvel.data.model.series.SeriesResult
import com.chocolatecake.marvel.data.model.stories.StoriesResult
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelService {
    @GET("comics")
    fun getComics(): Single<Response<BaseResponse<ComicsResult>>>

    @GET("comics/{comicId}")
    fun getComicsById(
        @Path("comicId") comicId: Int,
    ): Single<Response<BaseResponse<ComicsResult>>>

    @GET("comics/{comicId}/characters")
    fun getComicsCharacters(
        @Path("comicId") comicId: Int,
    ): Single<Response<BaseResponse<ComicsResult>>>


    @GET("series")
    fun getSeries(): Single<Response<BaseResponse<SeriesResult>>>

    @GET("series/{seriesId}")
    fun getSeriesById(
        @Path("seriesId") seriesId: Int,
    ): Single<Response<BaseResponse<SeriesResult>>>

    @GET("series/{seriesId}/characters")
    fun getSeriesCharacters(
        @Path("seriesId") seriesId: Int,
    ): Single<Response<BaseResponse<SeriesResult>>>

    @GET("series/{seriesId}/comics")
    fun getSeriesComics(
        @Path("seriesId") seriesId: Int,
    ): Single<Response<BaseResponse<SeriesResult>>>


    @GET("stories")
    fun getStories(): Single<Response<BaseResponse<StoriesResult>>>

    @GET("stories/{storiesId}")
    fun getStoriesById(
        @Path("storiesId") storiesId: Int,
    ): Single<Response<BaseResponse<StoriesResult>>>

    @GET("stories/{storiesId}/characters")
    fun getStoriesCharacters(
        @Path("storiesId") storiesId: Int,
    ): Single<Response<BaseResponse<StoriesResult>>>

    @GET("stories/{storiesId}/comics")
    fun getStoriesComics(
        @Path("storiesId") storiesId: Int,
    ): Single<Response<BaseResponse<StoriesResult>>>
}