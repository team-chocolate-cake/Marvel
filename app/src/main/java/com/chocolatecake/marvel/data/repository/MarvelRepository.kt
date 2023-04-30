package com.chocolatecake.marvel.data.repository

import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.model.StoriesResult
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
    fun getStoriesById(storyId: Int,): Single<State<BaseResponse<StoriesResult>?>>
    fun getStoriesCharacters(storyId: Int,): Single<State<BaseResponse<StoriesResult>?>>
    fun getStoriesComics(storyId: Int,): Single<State<BaseResponse<StoriesResult>?>>
}