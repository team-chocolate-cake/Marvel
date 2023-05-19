package com.chocolatecake.marvel.ui.series_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.data.remote.model.dto.EventDto
import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.domain.model.Character
import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.domain.model.Event
import com.chocolatecake.marvel.domain.model.SeriesDetails
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.series_details.adapters.SeriesDetailsListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeriesDetailsViewModel @Inject constructor(
    private val repository: MarvelRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(), SeriesDetailsListener {

    private val seriesId: Int = savedStateHandle[SERIES_ID] ?: 0

    private val _series = MutableLiveData<Status<SeriesDetails?>>()
    val series: LiveData<Status<SeriesDetails?>> = _series

    private val _characters = MutableLiveData<Status<List<Character?>>>()
    val characters: LiveData<Status<List<Character?>>> = _characters

    private val _comics = MutableLiveData<Status<List<Comic?>>>()
    val comics: LiveData<Status<List<Comic?>>> = _comics

    private val _events = MutableLiveData<Status<List<Event?>>>()
    val events: LiveData<Status<List<Event?>>> = _events

    init {
        loadData()
    }

    fun loadData() {
        getSeriesById()
        getCharactersForSeries()
        getComicsForSeries()
        getEventsForSeries()
    }

    //region Series
    private fun getSeriesById() {
        _series.postValue(Status.Loading)
        disposeResponse(
            response = repository.getSeriesById(seriesId),
            onSuccess = ::onSeriesSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onSeriesSuccess(status: Status<SeriesDetails>) {
        status.toData()?.let {
            _series.postValue(Status.Success(it))
        }
    }
    //endregion

    //region Characters
    private fun getCharactersForSeries() {
        _characters.postValue(Status.Loading)
        disposeResponse(
            response = repository.getCharactersForSeries(seriesId),
            onSuccess = ::onCharactersSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onCharactersSuccess(status: Status<List<Character>>) {
        status.toData()?.let {
            _characters.postValue(Status.Success(it))
        }
    }
    //endregion

    //region Comics
    private fun getComicsForSeries() {
        _comics.postValue(Status.Loading)
        disposeResponse(
            response = repository.getComicsForSeries(seriesId),
            onSuccess = ::onComicsSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onComicsSuccess(status: Status<List<Comic>>) {
        status.toData()?.let {
            _comics.postValue(Status.Success(it))
        }
    }
    //endregion

    //region Event
    private fun getEventsForSeries() {
        _events.postValue(Status.Loading)
        disposeResponse(
            response = repository.getEventsForSeries(seriesId),
            onSuccess = ::onEventsSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onEventsSuccess(status: Status<List<Event>>) {
        status.toData()?.let {
            _events.postValue(Status.Success(it))
        }
    }
    //endregion

    private fun onFailure(throwable: Throwable) {
        _events.postValue(Status.Failure(throwable.message.toString()))
        _series.postValue(Status.Failure(throwable.message.toString()))
        _comics.postValue(Status.Failure(throwable.message.toString()))
        _characters.postValue(Status.Failure(throwable.message.toString()))
    }


    override fun onClickEvent(id: Int) {
        navigate(
            SeriesDetailsFragmentDirections.actionSeriesDetailsFragmentToEventDetailsFragment(
                id
            )
        )
    }

    override fun onClickComic(id: Int) {
        navigate(
            SeriesDetailsFragmentDirections.actionSeriesFragmentToComicsDetailsFragment(
                id
            )
        )
    }

    override fun onClickCharacter(id: Int) {
        navigate(
            SeriesDetailsFragmentDirections.actionSeriesDetailsFragmentToCharacterDetailsFragment(
                id
            )
        )
    }

    private companion object {
        const val SERIES_ID = "seriesId"
    }
}
