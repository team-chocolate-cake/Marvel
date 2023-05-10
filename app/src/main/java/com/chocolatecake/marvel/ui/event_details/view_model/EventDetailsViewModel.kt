package com.chocolatecake.marvel.ui.event_details.view_model

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
import com.chocolatecake.marvel.ui.event_details.view.EventDetailsListener

class EventDetailsViewModel : BaseViewModel(), EventDetailsListener {

    private val repository: MarvelRepository by lazy { MarvelRepositoryImpl() }

    private val _event = MutableLiveData<Status<EventResult>>()
    val event: LiveData<Status<EventResult>> get() = _event

    private val _characters = MutableLiveData<Status<List<ProfileResult>>>()
    val characters: MutableLiveData<Status<List<ProfileResult>>> get() = _characters

    private val _series = MutableLiveData<Status<List<SeriesResult>>>()
    val series: MutableLiveData<Status<List<SeriesResult>>> get() = _series

    private val _comics = MutableLiveData<Status<List<ComicsResult>>>()
    val comics: MutableLiveData<Status<List<ComicsResult>>> get() = _comics

    private val _characterId = MutableLiveData<Int?>()
    val characterId: MutableLiveData<Int?> get() = _characterId

    private val _seriesId = MutableLiveData<Int?>()
    val seriesId: MutableLiveData<Int?> get() = _seriesId

    private val _comicsId = MutableLiveData<Int?>()
    val comicsId: MutableLiveData<Int?> get() = _comicsId

    init {
        reLoadData()
    }

    fun reLoadData() {
        getEventDetails()
        getCharactersByEventId()
        getComicsByEventId()
        getSeriesByEventId()
    }

    private fun getEventDetails() {
        repository.getSpecificEventByEventId(293)
            .subscribe(::onEventSuccess, ::onFailure).add()
    }

    private fun onEventSuccess(status: Status<BaseResponse<EventResult>?>) {
        status.toData()?.data?.results?.first()?.let {
            _event.postValue(Status.Success(it))
        }
    }

    private fun getCharactersByEventId() {
        repository.getCharactersByEventId(293)
            .subscribe(::onCharactersSuccess, ::onFailure).add()
    }

    private fun onCharactersSuccess(status: Status<BaseResponse<ProfileResult>?>) {
        status.toData()?.data?.results?.let {
            _characters.postValue(Status.Success(it.filterNotNull()))
        }
    }

    private fun getSeriesByEventId() {
        repository.getSeriesByEventId(293)
            .subscribe(::onSeriesSuccess, ::onFailure).add()
    }

    private fun onSeriesSuccess(status: Status<BaseResponse<SeriesResult>?>) {
        status.toData()?.data?.results?.let {
            _series.postValue(Status.Success(it.filterNotNull()))
        }
    }

    private fun getComicsByEventId() {
        repository.getComicsByEventId(293).subscribe(::onComicsSuccess, ::onFailure).add()
    }

    private fun onComicsSuccess(status: Status<BaseResponse<ComicsResult>?>) {
        status.toData()?.data?.results?.let {
            _comics.postValue(Status.Success(it.filterNotNull()))
        }
    }

    private fun onFailure(throwable: Throwable) {
        _event.postValue(Status.Failure(throwable.message.toString()))
        _series.postValue(Status.Failure(throwable.message.toString()))
        _comics.postValue(Status.Failure(throwable.message.toString()))
        _characters.postValue(Status.Failure(throwable.message.toString()))
    }

    override fun onClickComic(id: Int) {
        _comicsId.postValue(id)
    }

    override fun onClickCharacter(id: Int) {
        _characterId.postValue(id)
    }

    override fun onClickSeries(id: Int) {
        _seriesId.postValue(id)
    }
}