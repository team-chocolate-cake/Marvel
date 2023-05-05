package com.chocolatecake.marvel.ui.home.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.home.view.HomeListener

class HomeViewModel : BaseViewModel(), HomeListener {
    private val marvelRepository: MarvelRepository by lazy { MarvelRepositoryImpl() }

    private val _events = MutableLiveData<Status<List<EventResult>>>()
    val events: LiveData<Status<List<EventResult>>> get() = _events

    private val _eventId = MutableLiveData<Int?>()
    val eventId: LiveData<Int?> get() = _eventId

    init {
        loadData()
    }

    fun loadData() {
        getCurrentEvent()
    }

    private fun getCurrentEvent() {
        _events.postValue(Status.Loading)
        marvelRepository.getEvents(limit = 10, offset = (0..50).random())
            .subscribe(::onEventSuccess, ::onFailure).add()
    }

    private fun onEventSuccess(result: Status<BaseResponse<EventResult>?>) {
        result.toData()?.data?.results?.let {
            _events.postValue(Status.Success(it.filterNotNull()))
        }
    }


    private fun onFailure(throwable: Throwable) {
        _events.postValue(Status.Failure(throwable.message.toString()))
    }

    override fun onClickBanner(eventId: Int?) {
        _eventId.postValue(eventId)
    }

}