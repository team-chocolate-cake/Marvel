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

    val characters = MutableLiveData<Status<List<ProfileResult?>>>()
    val series = MutableLiveData<Status<List<SeriesResult>>>()
    val comics = MutableLiveData<Status<List<ComicsResult>>>()
    val event = MutableLiveData<Status<EventResult>>()

    val itemList = MutableLiveData<MutableList<EventDetailsItem>>()

    init {
        initItems()
        getCharactersByEventId()
        getEventDetails()
        getComicsByEventId()
        getSeriesByEventId()
    }

    private fun initItems() {
      /*  with(itemList.value) {
            this?.add(EventDetailsItem.Header(event.value?.toData()))
            Log.d("Mimoo", this.toString())
            this?.add(EventDetailsItem.Character(this@EventDetailsViewModel))
            this?.add(EventDetailsItem.Series(this@EventDetailsViewModel))
            this?.add(EventDetailsItem.Comics(this@EventDetailsViewModel))
        }*/
        val myItemsList = mutableListOf(
            EventDetailsItem.Header(event.value?.toData()),
            EventDetailsItem.Character(this@EventDetailsViewModel),
            EventDetailsItem.Series(this@EventDetailsViewModel),
            EventDetailsItem.Comics(this@EventDetailsViewModel)
        )
        itemList.postValue(myItemsList)
    }


    private fun getEventDetails() {
        repository.getSpecificEventByEventId(293).subscribe(
            { responseStatus ->
                responseStatus.toData()?.data?.results?.first()?.let {
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
            {
                it.toData()?.data?.results?.let {
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
            {
                it.toData()?.data?.results?.let {
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
            {
                it.toData()?.data?.results?.let {
                    comics.postValue(Status.Success(it.filterNotNull()))
                    Log.d("Mimo", it.toString())
                }
            }, {
                Log.d("Mimo", it.toString())
            }
        ).add()
    }

    override fun onClickCharacter(characterId: Int?) {
        Log.d("Mimo", characterId.toString())
    }

    override fun onClickSeries(seriesId: Int?) {
        Log.d("Mimo", seriesId.toString())
    }

    override fun onClickComics(seriesId: Int?) {
        Log.d("Mimo", seriesId.toString())
    }

}