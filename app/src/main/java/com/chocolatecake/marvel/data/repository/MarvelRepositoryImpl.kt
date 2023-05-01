package com.chocolatecake.marvel.data.repository

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.model.StoriesResult
import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.remote.service.MarvelApi
import com.chocolatecake.marvel.data.util.State
import com.chocolatecake.marvel.util.observeOnMainThread
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class MarvelRepositoryImpl : MarvelRepository {
    private val apiService by lazy { MarvelApi().apiService }

    override fun getComics(
        title: String?,
        limit: Int?
    ): Single<State<BaseResponse<ComicsResult>?>> {
        return wrapperToState(apiService.getComics(title, limit))
    }

    override fun getComicById(comicId: Int): Single<State<BaseResponse<ComicsResult>?>> {
        return wrapperToState(apiService.getComicById(comicId))
    }

    override fun getCharactersForComic(comicId: Int): Single<State<BaseResponse<ProfileResult>?>> {
        return wrapperToState(apiService.getCharactersForComic(comicId))
    }

    override fun getCharacters(
        name: String?,
        limit: Int?
    ): Single<State<BaseResponse<ProfileResult>?>> {
        return wrapperToState(apiService.getCharacters(name, limit))
    }

    override fun getCharacterById(characterId: Int): Single<State<BaseResponse<ProfileResult>?>> {
        return wrapperToState(apiService.getCharacterById(characterId))
    }

    override fun getComicsForCharacter(characterId: Int): Single<State<BaseResponse<ComicsResult>?>> {
        return wrapperToState(apiService.getComicsForCharacter(characterId))
    }

    override fun getCharacterSeries(characterId: Int): Single<State<BaseResponse<SeriesResult>?>> {
        return wrapperToState(apiService.getSeriesForCharacter(characterId))
    }

    override fun getCreators(
        firstName: String?,
        middleName: String?,
        lastName: String?
    ): Single<State<BaseResponse<ProfileResult>?>> {
        return wrapperToState(apiService.getCreators(firstName, middleName, lastName))
    }

    override fun getCreatorById(creatorId: Int): Single<State<BaseResponse<ComicsResult>?>> {
        return wrapperToState(apiService.getCreatorById(creatorId))
    }

    override fun getComicsForCreator(creatorId: Int): Single<State<BaseResponse<ComicsResult>?>> {
        return wrapperToState(apiService.getComicsForCreator(creatorId))
    }

    override fun getSeriesForCreator(creatorId: Int): Single<State<BaseResponse<SeriesResult>?>> {
        return wrapperToState(apiService.getSeriesForCreator(creatorId))
    }

    override fun getSeries(
        title: String?,
        limit: Int?
    ): Single<State<BaseResponse<SeriesResult>?>> {
        return wrapperToState(apiService.getSeries(title, limit))
    }

    override fun getSeriesById(seriesId: Int): Single<State<BaseResponse<SeriesResult>?>> {
        return wrapperToState(apiService.getSeriesById(seriesId))
    }

    override fun getCharactersForSeries(seriesId: Int): Single<State<BaseResponse<SeriesResult>?>> {
        return wrapperToState(apiService.getCharactersForSeries(seriesId))
    }

    override fun getComicsForSeries(seriesId: Int): Single<State<BaseResponse<SeriesResult>?>> {
        return wrapperToState(apiService.getComicsForSeries(seriesId))
    }

    override fun getStories(): Single<State<BaseResponse<StoriesResult>?>> {
        return wrapperToState(apiService.getStories())
    }

    override fun getStoryById(storyId: Int): Single<State<BaseResponse<StoriesResult>?>> {
        return wrapperToState(apiService.getStoryById(storyId))
    }

    override fun getCharactersForStory(storyId: Int): Single<State<BaseResponse<StoriesResult>?>> {
        return wrapperToState(apiService.getCharactersForStory(storyId))
    }

    override fun getComicsForStory(storyId: Int): Single<State<BaseResponse<StoriesResult>?>> {
        return wrapperToState(apiService.getComicsForStory(storyId))
    }

    private fun <T> wrapperToState(response: Single<Response<BaseResponse<T>>>)
            : Single<State<BaseResponse<T>?>> {
        return response.map {
            try {
                if (it.isSuccessful) {
                    State.Success(it.body())
                } else {
                    State.Failure(it.message())
                }
            } catch (e: Exception) {
                State.Failure(e.message.toString())
            }
        }.observeOnMainThread()
    }
}