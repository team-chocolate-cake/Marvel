package com.chocolatecake.marvel.ui.series_details.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.series_details.view.SeriesDetailsFragmentDirections
import com.chocolatecake.marvel.ui.series_details.view.SeriesDetailsListener

class SeriesDetailsViewModel(
    private val seriesId: Int,
) : BaseViewModel(), SeriesDetailsListener {

    val repository: MarvelRepository by lazy { MarvelRepositoryImpl() }

    private val _series = MutableLiveData<Status<SeriesResult?>>()
    val series: LiveData<Status<SeriesResult?>>
        get() = _series

    private val _characters = MutableLiveData<Status<List<ProfileResult?>>>()
    val characters: LiveData<Status<List<ProfileResult?>>>
        get() = _characters

    private val _comics = MutableLiveData<Status<List<ComicsResult?>>>()
    val comics: LiveData<Status<List<ComicsResult?>>>
        get() = _comics

    private val _events = MutableLiveData<Status<List<EventResult?>>>()
    val events: LiveData<Status<List<EventResult?>>>
        get() = _events


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

    private fun onSeriesSuccess(status: Status<List<SeriesResult>>) {
        status.toData()?.first()?.let {
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

    private fun onCharactersSuccess(status: Status<List<ProfileResult>>) {
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

    private fun onComicsSuccess(status: Status<List<ComicsResult>>) {
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

    private fun onEventsSuccess(status: Status<List<EventResult>>) {
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
}
