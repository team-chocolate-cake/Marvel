package com.chocolatecake.marvel.data.repository

import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.model.comics.ComicsResult
import com.chocolatecake.marvel.data.model.series.SeriesResult
import com.chocolatecake.marvel.data.model.stories.StoriesResult
import com.chocolatecake.marvel.data.remote.service.MarvelService
import com.chocolatecake.marvel.data.util.State
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class MarvelRepositoryImpl(private val apiService: MarvelService) : MarvelRepository{
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

    override fun getStoriesById(storiesId: Int): Single<State<BaseResponse<StoriesResult>?>> {
        return wrapperToState(apiService.getStoriesById(storiesId))
    }

    override fun getStoriesCharacters(storiesId: Int): Single<State<BaseResponse<StoriesResult>?>> {
        return wrapperToState(apiService.getStoriesCharacters(storiesId))
    }

    override fun getStoriesComics(storiesId: Int): Single<State<BaseResponse<StoriesResult>?>> {
        return wrapperToState(apiService.getStoriesComics(storiesId))
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
        }
    }
}