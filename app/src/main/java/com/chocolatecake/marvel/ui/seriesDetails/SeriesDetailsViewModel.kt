package com.chocolatecake.marvel.ui.seriesDetails

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

class SeriesDetailsViewModel : BaseViewModel() {

    val repository: MarvelRepository by lazy { MarvelRepositoryImpl() }
    val series = MutableLiveData<Status<SeriesResult>>()
    val characters = MutableLiveData<Status<List<ProfileResult?>>>()
    val comics = MutableLiveData<Status<List<ComicsResult?>>>()
    val events = MutableLiveData<Status<List<EventResult?>>>()

    val itemList: MutableList<SeriesDetailsItem> = mutableListOf()

    init {
        getSeriesById()
        getCharactersForSeries()
        getComicsForSeries()
        getEventsForSeries()
    }

    fun getSeriesById() {

        repository.getSeriesById(31445)
            .subscribe({ responseStatus ->
                responseStatus.toData()?.data?.results?.first()?.let {
                    Log.e("SuccessHere", it.toString())
                    series.postValue(Status.Success(it))
                }
            }, {
                Log.e("najeia", it.toString())
            }).add()
    }

    fun getCharactersForSeries() {
        repository.getCharactersForSeries(31445)
            .subscribe({
                it.toData()?.data?.results?.let {
                    Log.e("SuccessHere", it.toString())
                    characters.postValue(Status.Success(it))
                }
            }, {
                Log.e("najeia", it.toString())

            }).add()
    }

    fun getComicsForSeries() {
        repository.getComicsForSeries(31445)
            .subscribe({ it ->
                it.toData()?.data?.results?.let {
                    Log.e("SuccessHere", it.toString())
                    comics.postValue(Status.Success(it))
                }
            }, {
                Log.e("najeia", it.toString())
            }
            ).add()
    }

    fun getEventsForSeries() {
        repository.getEventsForSeries(31445)
            .subscribe({ it ->
                it.toData()?.data?.results?.let {
                    Log.e("SuccessHere", it.toString())
                    events.postValue(Status.Success(it))
                }
            }, {
                Log.e("najeia", it.toString())
            }).add()
    }

}