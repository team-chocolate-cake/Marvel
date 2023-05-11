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

class SeriesDetailsViewModel : BaseViewModel(), SeriesDetailsListener {

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

    fun getSeriesById() {
        repository.getSeriesById(SERIES_ITEM_ID)
            .subscribe(::onSeriesSuccess, ::onFailure).add()
    }

    private fun onSeriesSuccess(status: Status<List<SeriesResult>>) {
        status.toData()?.first()?.let {
            _series.postValue(Status.Success(it))
        }
    }

    fun getCharactersForSeries() {
        repository.getCharactersForSeries(SERIES_ITEM_ID)
            .subscribe(::onCharactersSuccess, ::onFailure).add()
    }

    private fun onCharactersSuccess(status: Status<List<ProfileResult>>) {
        status.toData()?.let {
            _characters.postValue(Status.Success(it))
        }
    }

    fun getComicsForSeries() {
        repository.getComicsForSeries(SERIES_ITEM_ID)
            .subscribe(::onComicsSuccess, ::onFailure).add()
    }

    private fun onComicsSuccess(status: Status<List<ComicsResult>>) {
        status.toData()?.let {
            _comics.postValue(Status.Success(it))
        }
    }

    fun getEventsForSeries() {
        repository.getEventsForSeries(SERIES_ITEM_ID)
            .subscribe(::onEventsSuccess, ::onFailure).add()
    }

    private fun onEventsSuccess(status: Status<List<EventResult>>) {
        status.toData()?.let {
            _events.postValue(Status.Success(it))
        }
    }

    private fun onFailure(throwable: Throwable) {
        _events.postValue(Status.Failure(throwable.message.toString()))
        _series.postValue(Status.Failure(throwable.message.toString()))
        _comics.postValue(Status.Failure(throwable.message.toString()))
        _characters.postValue(Status.Failure(throwable.message.toString()))
    }

    override fun onClickEvent(id: Int) {
        //  navigate(
//            CharacterDetailsFragment.actionFirstSceneFragmentToSecondSceneFragment(
//                characterId = id
//            )
    }

    override fun onClickComic(id: Int) {
        navigate(
            SeriesDetailsFragmentDirections.actionSeriesFragmentToComicsDetailsFragment(
                comicId = id
            )
        )
    }

    override fun onClickCharacter(id: Int) {
        //  navigate(
//            CharacterDetailsFragment.actionFirstSceneFragmentToSecondSceneFragment(
//                characterId = id
//            )
    }

    companion object {
        private const val SERIES_ITEM_ID = 2541
    }
}
