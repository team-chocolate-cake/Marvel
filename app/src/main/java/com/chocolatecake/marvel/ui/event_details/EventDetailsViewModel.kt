package com.chocolatecake.marvel.ui.event_details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel

class EventDetailsViewModel : BaseViewModel() {

    private val repository: MarvelRepository by lazy { MarvelRepositoryImpl() }

    val characters = MutableLiveData<List<ProfileResult?>?>()

    init {
        getCharactersByEventId()
    }

    private fun getCharactersByEventId() {
        repository.getCharactersByEventId(293).subscribe(
            {
                characters.postValue(it.toData()?.data?.results)
                Log.d("Mimo", it.toData()?.data?.results.toString())
            }, {
                Log.d("Mimo", it.toString())
            }
        ).add()
    }

    private fun getSeriesByEventId() {

    }

    private fun getComicsByEventId() {

    }

    private fun getEventDetails() {

    }

}