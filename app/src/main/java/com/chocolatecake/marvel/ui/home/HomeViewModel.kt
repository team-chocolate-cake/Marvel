package com.chocolatecake.marvel.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.domain.model.Event
import com.chocolatecake.marvel.domain.model.Series
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.home.adapter.HomeListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val marvelRepository: MarvelRepository
) : BaseViewModel(), HomeListener {

    private val _events = MutableLiveData<Status<List<Event>>>()
    val events: LiveData<Status<List<Event>>>
        get() = _events

    private val _series = MutableLiveData<Status<List<Series>>>()
    val series: LiveData<Status<List<Series>>>
        get() = _series

    private val _comics = MutableLiveData<Status<List<Comic>>>()
    val comics: LiveData<Status<List<Comic>>>
        get() = _comics

    init {
        loadData()
    }

    fun loadData() {
        getCurrentSeries()
        getCurrentEvent()
        getCurrentComic()
    }

    //region Event
    private fun getCurrentEvent() {
        _events.postValue(Status.Loading)
        marvelRepository.refreshEvent(limit = LIMIT_EVENT, offset = (0..50).random())
            .subscribe({}, {}).add()
        disposeObservableResponse(
            response = marvelRepository.getEvents(),
            onSuccess = ::onEventSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onEventSuccess(result: Status<List<Event>>) {
        result.toData()?.let {
            _events.postValue(Status.Success(it))
        }
    }
    //endregion

    //region Series
    private fun getCurrentSeries() {
        _series.postValue(Status.Loading)
        marvelRepository
            .refreshSeries(limit = LIMIT, offset = (0..50).random())
            .subscribe({}, {}).add()
        disposeObservableResponse(
            response = marvelRepository.getSeries(),
            onSuccess = ::onSeriesSuccess,
            onFailure = ::onFailure
        )
    }

    private fun onSeriesSuccess(status: Status<List<Series>>) {
        status.toData()?.let {
            _series.postValue(Status.Success(it))
        }
    }
    //endregion

    //region Comic
    private fun getCurrentComic() {
        _comics.postValue(Status.Loading)
        marvelRepository.refreshComics(limit = LIMIT, offset = (0..50).random()).subscribe({}, {})
            .add()
        disposeObservableResponse(
            response = marvelRepository.getComics(),
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