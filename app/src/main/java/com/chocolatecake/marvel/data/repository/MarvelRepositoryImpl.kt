package com.chocolatecake.marvel.data.repository

import com.chocolatecake.marvel.data.local.MarvelDao
import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.data.remote.model.dto.EventDto
import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.data.remote.model.dto.SeriesDto
import com.chocolatecake.marvel.data.remote.model.dto.StoryDto
import com.chocolatecake.marvel.data.remote.service.MarvelService
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.domain.mapper.character.CharacterMapper
import com.chocolatecake.marvel.domain.mapper.character.CharacterUIMapper
import com.chocolatecake.marvel.domain.mapper.comic.ComicDetailsDtoToUiMapper
import com.chocolatecake.marvel.domain.mapper.comic.ComicMapper
import com.chocolatecake.marvel.domain.mapper.comic.ComicUIMapper
import com.chocolatecake.marvel.domain.mapper.event.EventDtoToUIMapper
import com.chocolatecake.marvel.domain.mapper.event.EventMapper
import com.chocolatecake.marvel.domain.mapper.event.EventUIMapper
import com.chocolatecake.marvel.domain.mapper.series.SeriesMapper
import com.chocolatecake.marvel.domain.mapper.series.SeriesUIMapper
import com.chocolatecake.marvel.domain.mapper.story.StoryMapper
import com.chocolatecake.marvel.domain.mapper.story.StoryUIMapper
import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.domain.model.ComicDetails
import com.chocolatecake.marvel.domain.model.Event
import com.chocolatecake.marvel.domain.model.Series
import com.chocolatecake.marvel.domain.model.Story
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val apiService: MarvelService,
    private val dao: MarvelDao,
    private val characterMapper: CharacterMapper,
    private val characterUIMapper: CharacterUIMapper,
    private val seriesMapper: SeriesMapper,
    private val seriesUiMapper: SeriesUIMapper,
    private val eventMapper: EventMapper,
    private val eventUIMapper: EventUIMapper,
    private val eventDtoToUIMapper: EventDtoToUIMapper,
    private val comicMapper: ComicMapper,
    private val comicUIMapper: ComicUIMapper,
    private val comicDetailsDtoToUiMapper: ComicDetailsDtoToUiMapper,
    private val storiesMapper: StoryMapper,
    private val storiesUIMapper: StoryUIMapper,
) : MarvelRepository {

    /// region comics
    override fun getComics(
        title: String?,
        limit: Int,
    ): Observable<Status<List<Comic>>> {
        return dao.getComicsWithLimit(limit).map {
            it.takeIf { it.isNotEmpty() }?.let {
                Status.Success(it.map { item -> comicUIMapper.map(item) })
            }
                ?: Status.Failure("No Result")
        }
    }

    override fun refreshComics(title: String?, limit: Int?, offset: Int?): Completable {
        return apiService.getComics(title, limit, offset).flatMapCompletable { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull()
                        ?.map { item -> comicMapper.map(item) }
                        ?.let { items -> dao.insertComics(items) }
                )
                Completable.complete()
            }
        }
    }

    override fun getEventByComicId(comicId: Int): Single<Status<List<Event>>> {
        return apiService.getEventByComicId(comicId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.body()?.data?.results?.filterNotNull()?.let {
                Status.Success(it.map { item -> eventDtoToUIMapper.map(item) })
            } ?: Status.Failure(baseResponse.message())
        }
    }

    override fun getComicById(comicId: Int): Single<Status<ComicDetails>> {
        return apiService.getComicById(comicId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.body()?.data?.results?.filterNotNull()
                ?.firstOrNull()?.let {
                    Status.Success(comicDetailsDtoToUiMapper.map(it))
                } ?: Status.Failure(baseResponse.message())
        }
    }

    override fun getCharactersForComic(comicId: Int): Single<Status<List<ProfileDto>>> {
        return apiService.getCharactersForComic(comicId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }
    /// endregion


    /// region characters
    override fun getCharacters(
        name: String?,
        limit: Int?
    ): Single<Status<List<ProfileDto>>> {
        return apiService.getCharacters(name, limit).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }

    override fun getCharacterById(characterId: Int): Single<Status<List<ProfileDto>>> {
        return apiService.getCharacterById(characterId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }

    override fun getComicsForCharacter(characterId: Int): Single<Status<List<ComicDto>>> {
        return apiService.getComicsForCharacter(characterId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }

    override fun getCharacterSeries(characterId: Int): Single<Status<List<SeriesDto>>> {
        return apiService.getSeriesForCharacter(characterId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }
    /// endregion


    /// region creators
    override fun getCreators(
        firstName: String?,
        middleName: String?,
        lastName: String?
    ): Single<Status<List<ProfileDto>>> {
        return apiService.getCreators(firstName, middleName, lastName).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }

    override fun getCreatorById(creatorId: Int): Single<Status<List<ProfileDto>>> {
        return apiService.getCreatorById(creatorId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }

    override fun getComicsForCreator(creatorId: Int): Single<Status<List<ComicDto>>> {
        return apiService.getComicsForCreator(creatorId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }

    override fun getSeriesForCreator(creatorId: Int): Single<Status<List<SeriesDto>>> {
        return apiService.getSeriesForCreator(creatorId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }
    /// endregion


    /// region series
    override fun getSeries(limit: Int): Observable<Status<List<Series>>> {
        return dao.getSeriesWithLimit(limit).map {
            it.takeIf { it.isNotEmpty() }?.let {
                Status.Success(it.map { item -> seriesUiMapper.map(item) })
            } ?: Status.Failure("No Result")
        }
    }

    override fun refreshSeries(limit: Int, offset: Int): Completable {
        return apiService.getSeries(limit = limit, offset = offset)
            .flatMapCompletable { baseResponse ->
                baseResponse.takeIf { it.isSuccessful }.let {
                    Status.Success(
                        baseResponse.body()?.data?.results?.filterNotNull()
                            ?.map { item -> seriesMapper.map(item) }
                            ?.let { items -> dao.insertSeries(items) }
                    )
                    Completable.complete()
                }
            }
    }

    override fun getSeriesById(seriesId: Int): Single<Status<List<SeriesDto>>> {
        return apiService.getSeriesById(seriesId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }

    override fun getCharactersForSeries(seriesId: Int): Single<Status<List<ProfileDto>>> {
        return apiService.getCharactersForSeries(seriesId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }

    override fun getComicsForSeries(seriesId: Int): Single<Status<List<ComicDto>>> {
        return apiService.getComicsForSeries(seriesId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }
    /// endregion


    /// region stories
    override fun getStories(limit: Int?, offset: Int?): Observable<Status<List<Story>>> {
        return dao.getAllStories().map {
            it.takeIf { it.isNotEmpty() }?.let {
                Status.Success(it.map { item ->
                    storiesUIMapper
                        .map(item)
                })
            } ?: Status.Failure("No Result")
        }
    }

    override fun refreshStories(limit: Int?, offset: Int?): Completable {
        return apiService.getStories().flatMapCompletable { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull()
                        ?.map { item -> storiesMapper.map(item) }
                        ?.let { items -> dao.insertStories(items) }
                )
                Completable.complete()
            }
        }
    }

    override fun getStoryById(storyId: Int): Single<Status<List<StoryDto>>> {
        return apiService.getStoryById(storyId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }

    override fun getCreatorsByStoryId(storyId: Int): Single<Status<List<ProfileDto>>> {
        return apiService.getCreatorsByStoryId(storyId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }

    override fun getComicsByStoryId(storyId: Int): Single<Status<List<ComicDto>>> {
        return apiService.getComicsByStoryId(storyId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }

    override fun getSeriesByStoryId(storyId: Int): Single<Status<List<SeriesDto>>> {
        return apiService.getSeriesByStoryId(storyId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }
    /// endregion


    /// region events
    override fun getEvents(
        limit: Int,
        offset: Int?
    ): Observable<Status<List<Event>>> {
        return dao.getEventsWithLimit(limit).map {
            it.takeIf { it.isNotEmpty() }?.let {
                Status.Success(it.map { item -> eventUIMapper.map(item) })
            } ?: Status.Failure("No Result")
        }
    }

    override fun refreshEvent(limit: Int?, offset: Int?): Completable {
        return apiService.getEvents(limit, offset).flatMapCompletable { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull()
                        ?.map { item -> eventMapper.map(item) }
                        ?.let { items -> dao.insertEvent(items) }
                )
                Completable.complete()
            }
        }
    }

    override fun getCharactersByEventId(eventId: Int): Single<Status<List<ProfileDto>>> {
        return apiService.getCharactersByEventId(eventId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }

    override fun getSeriesByEventId(eventId: Int): Single<Status<List<SeriesDto>>> {
        return apiService.getSeriesByEventId(eventId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }

    override fun getComicsByEventId(eventId: Int): Single<Status<List<ComicDto>>> {
        return apiService.getComicsByEventId(eventId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }

    override fun getSpecificEventByEventId(eventId: Int): Single<Status<List<EventDto>>> {
        return apiService.getSpecificEventByEventId(eventId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }

    override fun getEventsForSeries(seriesId: Int): Single<Status<List<EventDto>>> {
        return apiService.getEventsForSeries(seriesId).map { baseResponse ->
            baseResponse.takeIf { it.isSuccessful }?.let {
                Status.Success(
                    baseResponse.body()?.data?.results?.filterNotNull() ?: emptyList()
                )
            } ?: Status.Failure(baseResponse.message())
        }
    }
    /// endregion
}