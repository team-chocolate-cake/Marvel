package com.chocolatecake.marvel.data.repository

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.model.StoryResult
import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.remote.service.MarvelApi
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.util.observeOnMainThread
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class MarvelRepositoryImpl : MarvelRepository {

    private val apiService by lazy { MarvelApi().apiService }

    override fun getComics(
        title: String?,
        limit: Int?,
        offset: Int?
    ): Single<Status<List<ComicsResult>>> {
        return wrapperToState(apiService.getComics(title, limit, offset))
    }

    override fun getEventByComicId(comicId: Int): Single<Status<List<EventResult>>> {
        return wrapperToState(apiService.getEventByComicId(comicId))
    }

    override fun getComicById(comicId: Int): Single<Status<List<ComicsResult>>> {
        return wrapperToState(apiService.getComicById(comicId))
    }


    override fun getCharactersForComic(comicId: Int): Single<Status<List<ProfileResult>>> {
        return wrapperToState(apiService.getCharactersForComic(comicId))
    }

    override fun getCharacters(
        name: String?,
        limit: Int?
    ): Single<Status<List<ProfileResult>>> {
        return wrapperToState(apiService.getCharacters(name, limit))
    }

    override fun getCharacterById(characterId: Int): Single<Status<List<ProfileResult>>> {
        return wrapperToState(apiService.getCharacterById(characterId))
    }

    override fun getComicsForCharacter(characterId: Int): Single<Status<List<ComicsResult>>> {
        return wrapperToState(apiService.getComicsForCharacter(characterId))
    }

    override fun getCharacterSeries(characterId: Int): Single<Status<List<SeriesResult>>> {
        return wrapperToState(apiService.getSeriesForCharacter(characterId))
    }


    override fun getCreators(
        firstName: String?,
        middleName: String?,
        lastName: String?
    ): Single<Status<List<ProfileResult>>> {
        return wrapperToState(apiService.getCreators(firstName, middleName, lastName))
    }

    override fun getCreatorById(creatorId: Int): Single<Status<List<ProfileResult>>> {
        return wrapperToState(apiService.getCreatorById(creatorId))
    }

    override fun getComicsForCreator(creatorId: Int): Single<Status<List<ComicsResult>>> {
        return wrapperToState(apiService.getComicsForCreator(creatorId))
    }

    override fun getSeriesForCreator(creatorId: Int): Single<Status<List<SeriesResult>>> {
        return wrapperToState(apiService.getSeriesForCreator(creatorId))
    }


    override fun getSeries(
        title: String?,
        offset: Int?,
        limit: Int?
    ): Single<Status<List<SeriesResult>>> {
        return wrapperToState(apiService.getSeries(title, offset, limit))
    }

    override fun getSeriesById(seriesId: Int): Single<Status<List<SeriesResult>>> {
        return wrapperToState(apiService.getSeriesById(seriesId))
    }

    override fun getCharactersForSeries(seriesId: Int): Single<Status<List<ProfileResult>>> {
        return wrapperToState(apiService.getCharactersForSeries(seriesId))
    }

    override fun getComicsForSeries(seriesId: Int): Single<Status<List<ComicsResult>>> {
        return wrapperToState(apiService.getComicsForSeries(seriesId))
    }


    override fun getEvents(
        limit: Int?,
        offset: Int?
    ): Single<Status<List<EventResult>>> {
        return wrapperToState(apiService.getEvents(limit, offset))
    }

    override fun getEventsForSeries(seriesId: Int): Single<Status<List<EventResult>>> {
        return wrapperToState(apiService.getEventsForSeries(seriesId))
    }


    override fun getStories(limit: Int?, offset: Int?): Single<Status<List<StoryResult>>> {
        return wrapperToState(apiService.getStories(limit, offset))
    }

    override fun getStoryById(storyId: Int): Single<Status<List<StoryResult>>> {
        return wrapperToState(apiService.getStoryById(storyId))
    }

    override fun getCreatorsByStoryId(storyId: Int): Single<Status<List<ProfileResult>>> {
        return wrapperToState(apiService.getCreatorsByStoryId(storyId))
    }

    override fun getComicsByStoryId(storyId: Int): Single<Status<List<ComicsResult>>> {
        return wrapperToState(apiService.getComicsByStoryId(storyId))
    }

    override fun getSeriesByStoryId(storyId: Int): Single<Status<List<SeriesResult>>> {
        return wrapperToState(apiService.getSeriesByStoryId(storyId))
    }


    override fun getCharactersByEventId(eventId: Int): Single<Status<List<ProfileResult>>> {
        return wrapperToState(apiService.getCharactersByEventId(eventId))
    }

    override fun getSeriesByEventId(eventId: Int): Single<Status<List<SeriesResult>>> {
        return wrapperToState(apiService.getSeriesByEventId(eventId))
    }

    override fun getComicsByEventId(eventId: Int): Single<Status<List<ComicsResult>>> {
        return wrapperToState(apiService.getComicsByEventId(eventId))
    }

    override fun getSpecificEventByEventId(eventId: Int): Single<Status<List<EventResult>>> {
        return wrapperToState(apiService.getSpecificEventByEventId(eventId))
    }


    private fun <T : Any> wrapperToState(response: Single<Response<BaseResponse<T>>>):
            Single<Status<List<T>>> {
        return response.map { baseResponse ->
            if (baseResponse.isSuccessful) {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList<T>()
                )
            } else {
                Status.Failure(baseResponse.message())
            }
        }.observeOnMainThread()
    }
}