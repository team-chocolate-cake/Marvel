package com.chocolatecake.marvel.data.repository

import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.model.StoriesResult
import com.chocolatecake.marvel.data.remote.service.MarvelApi
import com.chocolatecake.marvel.data.remote.service.MarvelService
import com.chocolatecake.marvel.data.util.State
import com.chocolatecake.marvel.util.observeOnMainThread
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class MarvelRepositoryImpl : MarvelRepository{

    private val apiService by lazy { MarvelApi().apiService }

    override fun getComics(): Single<State<BaseResponse<ComicsResult>?>> {
        return wrapperToState(apiService.getComics())
    }

    override fun getComicsById(comicId: Int): Single<State<BaseResponse<ComicsResult>?>> {
        return wrapperToState(apiService.getComicsById(comicId))
    }

    override fun getComicsCharacters(comicId: Int): Single<State<BaseResponse<ComicsResult>?>> {
        return wrapperToState(apiService.getComicsCharacters(comicId))
    }


    override fun getSeries(): Single<State<BaseResponse<SeriesResult>?>> {
        return wrapperToState(apiService.getSeries())
    }

    override fun getSeriesById(seriesId: Int): Single<State<BaseResponse<SeriesResult>?>> {
        return wrapperToState(apiService.getSeriesById(seriesId))
    }

    override fun getSeriesCharacters(seriesId: Int): Single<State<BaseResponse<SeriesResult>?>> {
        return wrapperToState(apiService.getSeriesCharacters(seriesId))
    }

    override fun getSeriesComics(seriesId: Int): Single<State<BaseResponse<SeriesResult>?>> {
        return wrapperToState(apiService.getSeriesComics(seriesId))
    }


    override fun getStories(): Single<State<BaseResponse<StoriesResult>?>> {
        return wrapperToState(apiService.getStories())
    }

    override fun getStoriesById(storyId: Int): Single<State<BaseResponse<StoriesResult>?>> {
        return wrapperToState(apiService.getStoriesById(storyId))
    }

    override fun getStoriesCharacters(storyId: Int): Single<State<BaseResponse<StoriesResult>?>> {
        return wrapperToState(apiService.getStoriesCharacters(storyId))
    }

    override fun getStoriesComics(storyId: Int): Single<State<BaseResponse<StoriesResult>?>> {
        return wrapperToState(apiService.getStoriesComics(storyId))
    }


    private fun <T> wrapperToState(response: Single<Response<BaseResponse<T>>>)
    :Single<State<BaseResponse<T>?>> {
        return response.map {
            try {
                if (it.isSuccessful) {
                    State.Success(it.body())
                } else {
                    State.Failure(it.message())
                }
            }catch (e: Exception){
                State.Failure(e.message.toString())
            }
        }.observeOnMainThread()
    }
}