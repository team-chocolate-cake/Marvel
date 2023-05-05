package com.chocolatecake.marvel.ui.seriesDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel

class SeriesDetailsViewModel : BaseViewModel(), SeriesDetailsListener {

    val repository: MarvelRepository by lazy { MarvelRepositoryImpl() }
    val itemList: MutableList<SeriesDetailsItem> = mutableListOf()

    private val _series = MutableLiveData<Status<SeriesResult?>>()
    private val _events = MutableLiveData<Status<List<EventResult?>>>()
    private val _characters = MutableLiveData<Status<List<ProfileResult?>>>()
    private val _comics = MutableLiveData<Status<List<ComicsResult?>>>()
    val events: LiveData<Status<List<EventResult?>>> get() = _events
    val series: LiveData<Status<SeriesResult?>> get() = _series
    val comics: LiveData<Status<List<ComicsResult?>>> get() = _comics
    val characters: LiveData<Status<List<ProfileResult?>>> get() = _characters


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
        _series.postValue(Status.Loading)
        repository.getSeriesById(2541)
            .subscribe(::onSeriesSuccess, ::onFailure).add()
    }

    private fun onSeriesSuccess(status: Status<BaseResponse<SeriesResult>?>) {
        status.toData()?.data?.results?.first()?.let {
            _series.postValue(Status.Success(it))
        }
    }

    fun getCharactersForSeries() {
        _characters.postValue(Status.Loading)
        repository.getCharactersForSeries(2541)
            .subscribe(::onCharactersSuccess, ::onFailure).add()
    }

    private fun onCharactersSuccess(status: Status<BaseResponse<ProfileResult>?>) {
        status.toData()?.data?.results?.let {
            _characters.postValue(Status.Success(it.filterNotNull()))
        }
    }

    fun getComicsForSeries() {
        _comics.postValue(Status.Loading)
        repository.getComicsForSeries(2541)
            .subscribe(
                ::onComicsSuccess, ::onFailure
            ).add()
    }

    private fun onComicsSuccess(status: Status<BaseResponse<ComicsResult>?>) {
        status.toData()?.data?.results?.let {
            _comics.postValue(Status.Success(it.filterNotNull()))
        }
    }

    fun getEventsForSeries() {
        _events.postValue(Status.Loading)
        repository.getEventsForSeries(2541)
            .subscribe(::onEventsSuccess, ::onFailure).add()
    }

    private fun onEventsSuccess(status: Status<BaseResponse<EventResult>?>) {
        status.toData()?.data?.results?.let {
            _events.postValue(Status.Success(it.filterNotNull()))
        }
    }

    private fun onFailure(throwable: Throwable) {
        _events.postValue(Status.Failure(throwable.message.toString()))
        _series.postValue(Status.Failure(throwable.message.toString()))
        _comics.postValue(Status.Failure(throwable.message.toString()))
        _characters.postValue(Status.Failure(throwable.message.toString()))
    }

    override fun onClickEvent(eventId: Int?) {
        Log.e("najeia", eventId.toString())
    }

    override fun onClickComic(comicId: Int?) {
        Log.e("najeia", comicId.toString())
    }

    override fun onClickCharacter(characterId: Int?) {
        Log.e("najeia", characterId.toString())
    }


}