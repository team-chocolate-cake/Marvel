package com.chocolatecake.marvel.ui.event_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.remote.model.ComicDto
import com.chocolatecake.marvel.data.remote.model.EventDto
import com.chocolatecake.marvel.data.remote.model.ProfileDto
import com.chocolatecake.marvel.data.remote.model.SeriesDto
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.event_details.adapters.EventDetailsListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    private val repository: MarvelRepository
) : BaseViewModel(), EventDetailsListener {

    var eventId by Delegates.notNull<Int>()

    private val _event = MutableLiveData<Status<EventDto>>()
    val event: LiveData<Status<EventDto>>
        get() = _event

    private val _characters = MutableLiveData<Status<List<ProfileDto>>>()
    val characters: MutableLiveData<Status<List<ProfileDto>>>
        get() = _characters

    private val _series = MutableLiveData<Status<List<SeriesDto>>>()
    val series: MutableLiveData<Status<List<SeriesDto>>>
        get() = _series

    private val _comics = MutableLiveData<Status<List<ComicDto>>>()
    val comics: MutableLiveData<Status<List<ComicDto>>>
        get() = _comics


    fun reLoadData() {
        getEventDetails()
        getCharactersByEventId()
        getComicsByEventId()
        getSeriesByEventId()
    }

    //region Event
    private fun getEventDetails() {
        _event.postValue(Status.Loading)
        disposeResponse(
            response = repository.getSpecificEventByEventId(eventId),
            onSuccess = ::onEventSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onEventSuccess(status: Status<List<EventDto>>) {
        status.toData()?.first()?.let {
            _event.postValue(Status.Success(it))
        }
    }
    //endregion

    //region Characters
    private fun getCharactersByEventId() {
        _characters.postValue(Status.Loading)
        disposeResponse(
            response = repository.getCharactersByEventId(eventId),
            onSuccess = ::onCharactersSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onCharactersSuccess(status: Status<List<ProfileDto>>) {
        status.toData()?.let {
            _characters.postValue(Status.Success(it))
        }
    }
    //endregion

    //region Series
    private fun getSeriesByEventId() {
        _series.postValue(Status.Loading)
        disposeResponse(
            response = repository.getSeriesByEventId(eventId),
            onSuccess = ::onSeriesSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onSeriesSuccess(status: Status<List<SeriesDto>>) {
        status.toData()?.let {
            _series.postValue(Status.Success(it))
        }
    }
    //endregion

    //region Comics
    private fun getComicsByEventId() {
        _comics.postValue(Status.Loading)
        disposeResponse(
            response = repository.getComicsByEventId(eventId),
            onSuccess = ::onComicsSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onComicsSuccess(status: Status<List<ComicDto>>) {
        status.toData()?.let {
            _comics.postValue(Status.Success(it))
        }
    }
    //endregion

    private fun onFailure(throwable: Throwable) {
        _event.postValue(Status.Failure(throwable.message.toString()))
        _series.postValue(Status.Failure(throwable.message.toString()))
        _comics.postValue(Status.Failure(throwable.message.toString()))
        _characters.postValue(Status.Failure(throwable.message.toString()))
    }


    override fun onClickComic(id: Int) {
        navigate(
            EventDetailsFragmentDirections.actionEventDetailsFragmentToComicsDetailsFragment(
                id
            )
        )
    }

    override fun onClickCharacter(id: Int) {
        navigate(
            EventDetailsFragmentDirections.actionEventDetailsFragmentToCharacterDetailsFragment(
                id
            )
        )
    }

    override fun onClickSeries(id: Int) {
        navigate(
            EventDetailsFragmentDirections.actionEventDetailsFragmentToSeriesDetailsFragment(
                id
            )
        )
    }
}