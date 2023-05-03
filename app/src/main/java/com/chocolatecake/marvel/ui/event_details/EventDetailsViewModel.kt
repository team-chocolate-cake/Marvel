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

    val characters  = MutableLiveData<Status<BaseResponse<ProfileResult>>>()
    private fun getCharactersByEventId() {
        repository.getCharactersByEventId(293).subscribe(
            {
                Log.d("Mimo", it.toString())
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