package com.chocolatecake.marvel.data.repository

import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.model.comics.ComicsResult
import com.chocolatecake.marvel.data.model.series.SeriesResult
import com.chocolatecake.marvel.data.model.stories.StoriesResult
import com.chocolatecake.marvel.data.util.State
import io.reactivex.rxjava3.core.Single

interface MarvelRepository {
    fun getComics(): Single<State<BaseResponse<ComicsResult>?>>
    fun getComicsById(comicId: Int): Single<State<BaseResponse<ComicsResult>?>>
    fun getComicsCharacters(comicId: Int): Single<State<BaseResponse<ComicsResult>?>>


    fun getSeries(): Single<State<BaseResponse<SeriesResult>?>>
    fun getSeriesById(seriesId: Int,): Single<State<BaseResponse<SeriesResult>?>>
    fun getSeriesCharacters(seriesId: Int,): Single<State<BaseResponse<SeriesResult>?>>
    fun getSeriesComics(seriesId: Int,): Single<State<BaseResponse<SeriesResult>?>>


    fun getStories(): Single<State<BaseResponse<StoriesResult>?>>
    fun getStoriesById(storiesId: Int,): Single<State<BaseResponse<StoriesResult>?>>
    fun getStoriesCharacters(storiesId: Int,): Single<State<BaseResponse<StoriesResult>?>>
    fun getStoriesComics(storiesId: Int,): Single<State<BaseResponse<StoriesResult>?>>
}