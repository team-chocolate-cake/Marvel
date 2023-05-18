package com.chocolatecake.marvel.data.repository

import com.chocolatecake.marvel.data.local.MarvelDataBase
import com.chocolatecake.marvel.data.remote.model.BaseResponse
import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.data.remote.model.dto.EventDto
import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.data.remote.model.dto.SeriesDto
import com.chocolatecake.marvel.data.remote.model.dto.StoryDto
import com.chocolatecake.marvel.data.remote.service.MarvelService
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.mapper.character.CharacterMapper
import com.chocolatecake.marvel.domain.mapper.character.CharacterUIMapper
import com.chocolatecake.marvel.domain.mapper.story.StoryMapper
import com.chocolatecake.marvel.domain.mapper.story.StoryUIMapper
import com.chocolatecake.marvel.domain.model.Story
import com.chocolatecake.marvel.util.observeOnMainThread
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val apiService: MarvelService,
    private val database: MarvelDataBase,
    private val characterMapper: CharacterMapper,
    private val characterUIMapper: CharacterUIMapper,
    private val storyMapper: StoryMapper,
    private val storyUIMapper: StoryUIMapper
) : MarvelRepository {


    /// region comics
    override fun getComics(
        title: String?,
        limit: Int?,
        offset: Int?
    ): Single<Status<List<ComicDto>>> {
        return wrapperToState(apiService.getComics(title, limit, offset))
    }

    override fun getEventByComicId(comicId: Int): Single<Status<List<EventDto>>> {
        return wrapperToState(apiService.getEventByComicId(comicId))
    }

    override fun getComicById(comicId: Int): Single<Status<List<ComicDto>>> {
        return wrapperToState(apiService.getComicById(comicId))
    }

    override fun getCharactersForComic(comicId: Int): Single<Status<List<ProfileDto>>> {
        return wrapperToState(apiService.getCharactersForComic(comicId))
    }
    /// endregion


    /// region characters
    override fun getCharacters(
        name: String?,
        limit: Int?
    ): Single<Status<List<ProfileDto>>> {
        return wrapperToState(apiService.getCharacters(name, limit))
    }

    override fun getCharacterById(characterId: Int): Single<Status<List<ProfileDto>>> {
        return wrapperToState(apiService.getCharacterById(characterId))
    }

    override fun getComicsForCharacter(characterId: Int): Single<Status<List<ComicDto>>> {
        return wrapperToState(apiService.getComicsForCharacter(characterId))
    }

    override fun getCharacterSeries(characterId: Int): Single<Status<List<SeriesDto>>> {
        return wrapperToState(apiService.getSeriesForCharacter(characterId))
    }
    /// endregion


    /// region creators
    override fun getCreators(
        firstName: String?,
        middleName: String?,
        lastName: String?
    ): Single<Status<List<ProfileDto>>> {
        return wrapperToState(apiService.getCreators(firstName, middleName, lastName))
    }

    override fun getCreatorById(creatorId: Int): Single<Status<List<ProfileDto>>> {
        return wrapperToState(apiService.getCreatorById(creatorId))
    }

    override fun getComicsForCreator(creatorId: Int): Single<Status<List<ComicDto>>> {
        return wrapperToState(apiService.getComicsForCreator(creatorId))
    }

    override fun getSeriesForCreator(creatorId: Int): Single<Status<List<SeriesDto>>> {
        return wrapperToState(apiService.getSeriesForCreator(creatorId))
    }
    /// endregion


    /// region series
    override fun getSeries(
        title: String?,
        offset: Int?,
        limit: Int?,
        orderBy: String?
    ): Single<Status<List<SeriesDto>>> {
        return wrapperToState(apiService.getSeries(title, offset, limit))
    }

    override fun getSeriesById(seriesId: Int): Single<Status<List<SeriesDto>>> {
        return wrapperToState(apiService.getSeriesById(seriesId))
    }

    override fun getCharactersForSeries(seriesId: Int): Single<Status<List<ProfileDto>>> {
        return wrapperToState(apiService.getCharactersForSeries(seriesId))
    }

    override fun getComicsForSeries(seriesId: Int): Single<Status<List<ComicDto>>> {
        return wrapperToState(apiService.getComicsForSeries(seriesId))
    }
    /// endregion


    /// region stories
    override fun getStories(limit: Int?, offset: Int?): Observable<Status<List<Story>>> {
        return wrapToState(
            dbCall = database.storyDao.getAllStories(),
            uiMapper = storyUIMapper
        )
    }

    override fun getStoryById(storyId: Int): Single<Status<List<StoryDto>>> {
        return wrapperToState(apiService.getStoryById(storyId))
    }

    override fun refreshStories(): Completable {
        return refreshData(
            response = apiService.getStories(),
            mapper = storyMapper,
            saveToDb = { database.storyDao.insertStories(it) },
        )

    }

    override fun getCreatorsByStoryId(storyId: Int): Single<Status<List<ProfileDto>>> {
        return wrapperToState(apiService.getCreatorsByStoryId(storyId))
    }

    override fun getComicsByStoryId(storyId: Int): Single<Status<List<ComicDto>>> {
        return wrapperToState(apiService.getComicsByStoryId(storyId))
    }

    override fun getSeriesByStoryId(storyId: Int): Single<Status<List<SeriesDto>>> {
        return wrapperToState(apiService.getSeriesByStoryId(storyId))
    }
    /// endregion


    /// region events
    override fun getEvents(
        limit: Int?,
        offset: Int?
    ): Single<Status<List<EventDto>>> {
        return wrapperToState(apiService.getEvents(limit, offset))
    }

    override fun getCharactersByEventId(eventId: Int): Single<Status<List<ProfileDto>>> {
        return wrapperToState(apiService.getCharactersByEventId(eventId))
    }

    override fun getSeriesByEventId(eventId: Int): Single<Status<List<SeriesDto>>> {
        return wrapperToState(apiService.getSeriesByEventId(eventId))
    }

    override fun getComicsByEventId(eventId: Int): Single<Status<List<ComicDto>>> {
        return wrapperToState(apiService.getComicsByEventId(eventId))
    }

    override fun getSpecificEventByEventId(eventId: Int): Single<Status<List<EventDto>>> {
        return wrapperToState(apiService.getSpecificEventByEventId(eventId))
    }

    override fun getEventsForSeries(seriesId: Int): Single<Status<List<EventDto>>> {
        return wrapperToState(apiService.getEventsForSeries(seriesId))
    }
    /// endregion


    /// region helpers
    private fun <I : Any, O : Any> wrapToState(
        dbCall: Observable<List<I>>,
        uiMapper: Mapper<I, O>
    ): Observable<Status<List<O>>> {
        return dbCall.map {
            if (it.isEmpty()) {
                return@map Status.Failure("No Result")
            }
            Status.Success(it.map { item -> uiMapper.map(item) })
        }.observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread())
    }

    private fun <T : Any, O : Any> refreshData(
        response: Single<Response<BaseResponse<T>>>,
        mapper: Mapper<T, O>,
        saveToDb: (List<O>) -> Completable,
    ): Completable {
        return response.flatMapCompletable { baseResponse ->
            if (baseResponse.isSuccessful) {
                val filteredItems = baseResponse.body()?.data?.results?.filterNotNull()
                val mappedItems = filteredItems?.map { mapper.map(it) }

                mappedItems?.let { items ->
                    saveToDb(items)
                } ?: Completable.error(Exception("Mapping error"))
            } else {
                Completable.error(Exception("API Error: ${baseResponse.code()}"))
            }
        }.onErrorResumeNext { error ->
            Completable.complete()
        }
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
    /// endregion
}