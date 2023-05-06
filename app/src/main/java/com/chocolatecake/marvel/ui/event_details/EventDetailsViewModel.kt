package com.chocolatecake.marvel.ui.event_details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.event_details.data.EventDetailsItem

class EventDetailsViewModel : BaseViewModel(), EventDetailsListener {

    private val repository: MarvelRepository by lazy { MarvelRepositoryImpl() }

    val event = MutableLiveData<Status<EventResult>>()
    val characters = MutableLiveData<Status<List<ProfileResult?>>>()
    val series = MutableLiveData<Status<List<SeriesResult>>>()
    val comics = MutableLiveData<Status<List<ComicsResult>>>()

    val _characterId = MutableLiveData<Int?>()
    val _seriesId = MutableLiveData<Int?>()
    val _comicsId = MutableLiveData<Int?>()

    val itemList: MutableList<EventDetailsItem> = mutableListOf()

    init {
        getEventDetails()
        getCharactersByEventId()
        getComicsByEventId()
        getSeriesByEventId()
    }

    private fun getEventDetails() {
        repository.getSpecificEventByEventId(293).subscribe(
            { responseStatus ->
                responseStatus.toData()?.data?.results?.first()?.let {
                    itemList.add(EventDetailsItem.Header(it))
                    event.postValue(Status.Success(it))
                    Log.d("Mimo", it.toString())
                }
            }, {
                Log.d("Mimo", it.toString())
            }
        ).add()
    }

    private fun getCharactersByEventId() {
        repository.getCharactersByEventId(293).subscribe(
            { it ->
                it.toData()?.data?.results?.let {
                    itemList.add(EventDetailsItem.Character(it))
                    characters.postValue(Status.Success(it.filterNotNull()))
                    Log.d("Mimo", it.toString())
                }
            }, {
                Log.d("Mimo", it.toString())
            }
        ).add()
    }

    private fun getSeriesByEventId() {
        repository.getSeriesByEventId(293).subscribe(
            { it ->
                it.toData()?.data?.results?.let {
                    itemList.add(EventDetailsItem.Series(it))
                    series.postValue(Status.Success(it.filterNotNull()))
                    Log.d("Mimo", it.toString())
                }
            }, {
                Log.d("Mimo", it.toString())
            }
        ).add()
    }

    private fun getComicsByEventId() {
        repository.getComicsByEventId(293).subscribe(
            { it ->
                it.toData()?.data?.results?.let {
                    itemList.add(EventDetailsItem.Comics(it))
                    comics.postValue(Status.Success(it.filterNotNull()))
                    Log.d("aaaaa", it.toString())
                }
            }, {
                Log.d("Mimo", it.toString())
            }
        ).add()
    }

    override fun onClickCharacter(characterId: Int?) {
        _characterId.postValue(characterId)
    }

    override fun onClickSeries(seriesId: Int?) {
        _seriesId.postValue(seriesId)
    }

    override fun onClickComics(comicsId: Int?) {
        _comicsId.postValue(comicsId)
    }

}