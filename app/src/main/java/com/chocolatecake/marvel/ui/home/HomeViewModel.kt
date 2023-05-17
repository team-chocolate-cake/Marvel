package com.chocolatecake.marvel.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.data.remote.model.dto.EventDto
import com.chocolatecake.marvel.data.remote.model.dto.SeriesDto
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.home.adapter.HomeListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val marvelRepository: MarvelRepository
) : BaseViewModel(), HomeListener {

    private val _events = MutableLiveData<Status<List<EventDto>>>()
    val events: LiveData<Status<List<EventDto>>>
        get() = _events

    private val _series = MutableLiveData<Status<List<SeriesDto>>>()
    val series: LiveData<Status<List<SeriesDto>>>
        get() = _series

    private val _comics = MutableLiveData<Status<List<ComicDto>>>()
    val comics: LiveData<Status<List<ComicDto>>>
        get() = _comics

    private val _eventId = MutableLiveData<Int?>()
    val eventId: LiveData<Int?>
        get() = _eventId

    private val _seriesId = MutableLiveData<Int?>()
    val seriesId: LiveData<Int?>
        get() = _seriesId

    private val _comicId = MutableLiveData<Int?>()
    val comicId: LiveData<Int?>
        get() = _comicId


    init {
        loadData()
    }

    fun loadData() {
        getCurrentEvent()
        getCurrentSeries()
        getCurrentComic()
    }

    //region Event
    private fun getCurrentEvent() {
        _events.postValue(Status.Loading)
        disposeResponse(
            response = marvelRepository.getEvents(limit = LIMIT_EVENT, offset = (0..50).random()),
            onSuccess = ::onEventSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onEventSuccess(result: Status<List<EventDto>>) {
        result.toData()?.let {
            _events.postValue(Status.Success(it))
        }
    }
    //endregion

    //region Series
    private fun getCurrentSeries() {
        _series.postValue(Status.Loading)
        disposeResponse(
            response = marvelRepository.getSeries(limit = LIMIT, offset = (0..50).random()),
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

    //region Comic
    private fun getCurrentComic() {
        _comics.postValue(Status.Loading)
        disposeResponse(
            response = marvelRepository.getComics(limit = LIMIT, offset = (0..50).random()),
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
        _events.postValue(Status.Failure(throwable.message.toString()))
        _series.postValue(Status.Failure(throwable.message.toString()))
        _comics.postValue(Status.Failure(throwable.message.toString()))
    }


    override fun onClickEvent(id: Int) {
        navigate(HomeFragmentDirections.actionHomeFragmentToEventDetailsFragment(id))
    }

    override fun onClickSeries(id: Int) {
        navigate(HomeFragmentDirections.actionHomeFragmentToSeriesDetailsFragment(id))
    }

    override fun onClickComic(id: Int) {
        navigate(HomeFragmentDirections.actionHomeFragmentToComicsDetailsFragment(id))
    }

    override fun onClickMoreComics() {
        navigate(HomeFragmentDirections.actionHomeFragmentToComicsFragment())
    }

    override fun onClickMoreSeries() {
        navigate(HomeFragmentDirections.actionHomeFragmentToLatestSeriesFragment())
    }


    private companion object {
        const val LIMIT = 4
        const val LIMIT_EVENT = 10
    }
}