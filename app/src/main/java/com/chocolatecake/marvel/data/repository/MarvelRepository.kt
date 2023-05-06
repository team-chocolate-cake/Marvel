package com.chocolatecake.marvel.data.repository

import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.model.E2ventResult
import com.chocolatecake.marvel.data.model.StoriesResult
import com.chocolatecake.marvel.data.util.Status
import io.reactivex.rxjava3.core.Single

interface MarvelRepository {
    fun getComics(
        title: String? = null,
        limit: Int? = null,
        offset: Int? = null,
    ): Single<Status<BaseResponse<ComicsResult>?>>

    fun getEventByComicId(comicId: Int):Single<Status<BaseResponse<E2ventResult>?>>

    fun getComicById(comicId: Int): Single<Status<BaseResponse<ComicsResult>?>>
    fun getCharactersForComic(comicId: Int): Single<Status<BaseResponse<ProfileResult>?>>
    fun getCharacters(
        name: String? = null,
        limit: Int? = null
    ): Single<Status<BaseResponse<ProfileResult>?>>

    fun getCharacterById(characterId: Int): Single<Status<BaseResponse<ProfileResult>?>>
    fun getComicsForCharacter(characterId: Int): Single<Status<BaseResponse<ComicsResult>?>>
    fun getCharacterSeries(characterId: Int): Single<Status<BaseResponse<SeriesResult>?>>

    fun getSeries(
        title: String? = null,
        limit: Int? = null
    ): Single<Status<BaseResponse<SeriesResult>?>>

    fun getSeriesById(seriesId: Int): Single<Status<BaseResponse<SeriesResult>?>>
    fun getCharactersForSeries(seriesId: Int): Single<Status<BaseResponse<SeriesResult>?>>
    fun getComicsForSeries(seriesId: Int): Single<Status<BaseResponse<SeriesResult>?>>

    fun getCreators(
        firstName: String? = null,
        middleName: String? = null,
        lastName: String? = null
    ): Single<Status<BaseResponse<ProfileResult>?>>

    fun getCreatorById(creatorId: Int): Single<Status<BaseResponse<ComicsResult>?>>
    fun getComicsForCreator(creatorId: Int): Single<Status<BaseResponse<ComicsResult>?>>
    fun getSeriesForCreator(
        creatorId: Int,
    ): Single<Status<BaseResponse<SeriesResult>?>>

    fun getStories(): Single<Status<BaseResponse<StoriesResult>?>>
    fun getStoryById(storyId: Int): Single<Status<BaseResponse<StoriesResult>?>>
    fun getCharactersForStory(storyId: Int): Single<Status<BaseResponse<StoriesResult>?>>
    fun getComicsForStory(storyId: Int): Single<Status<BaseResponse<StoriesResult>?>>
}