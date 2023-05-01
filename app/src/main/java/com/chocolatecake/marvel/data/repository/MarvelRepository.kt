package com.chocolatecake.marvel.data.repository

import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.model.StoriesResult
import com.chocolatecake.marvel.data.util.State
import io.reactivex.rxjava3.core.Single

interface MarvelRepository {
    fun getComics(
        title: String? = null,
        limit: Int? = null
    ): Single<State<BaseResponse<ComicsResult>?>>

    fun getComicById(comicId: Int): Single<State<BaseResponse<ComicsResult>?>>
    fun getCharactersForComic(comicId: Int): Single<State<BaseResponse<ProfileResult>?>>
    fun getCharacters(
        name: String? = null,
        limit: Int? = null
    ): Single<State<BaseResponse<ProfileResult>?>>

    fun getCharacterById(characterId: Int): Single<State<BaseResponse<ProfileResult>?>>
    fun getComicsForCharacter(characterId: Int): Single<State<BaseResponse<ComicsResult>?>>
    fun getCharacterSeries(characterId: Int): Single<State<BaseResponse<SeriesResult>?>>

    fun getSeries(
        title: String? = null,
        limit: Int? = null
    ): Single<State<BaseResponse<SeriesResult>?>>

    fun getSeriesById(seriesId: Int): Single<State<BaseResponse<SeriesResult>?>>
    fun getCharactersForSeries(seriesId: Int): Single<State<BaseResponse<SeriesResult>?>>
    fun getComicsForSeries(seriesId: Int): Single<State<BaseResponse<SeriesResult>?>>

    fun getCreators(
        firstName: String? = null,
        middleName: String? = null,
        lastName: String? = null
    ): Single<State<BaseResponse<ProfileResult>?>>

    fun getCreatorById(creatorId: Int): Single<State<BaseResponse<ComicsResult>?>>
    fun getComicsForCreator(creatorId: Int): Single<State<BaseResponse<ComicsResult>?>>
    fun getSeriesForCreator(
        creatorId: Int,
    ): Single<State<BaseResponse<SeriesResult>?>>

    fun getStories(): Single<State<BaseResponse<StoriesResult>?>>
    fun getStoryById(storyId: Int): Single<State<BaseResponse<StoriesResult>?>>
    fun getCharactersForStory(storyId: Int): Single<State<BaseResponse<StoriesResult>?>>
    fun getComicsForStory(storyId: Int): Single<State<BaseResponse<StoriesResult>?>>
}