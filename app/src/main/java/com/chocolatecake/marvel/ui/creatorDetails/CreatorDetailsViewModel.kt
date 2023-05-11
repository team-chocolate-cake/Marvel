package com.chocolatecake.marvel.ui.creatorDetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel

class CreatorDetailsViewModel(
    private val creatorId: Int
) : BaseViewModel(), CreatorDetailsListener {
    val comicsList = MutableLiveData<Status<List<ComicsResult>?>>()
    val seriesList = MutableLiveData<Status<List<SeriesResult>?>>()
    val creator = MutableLiveData<Status<List<ProfileResult>?>>()

    private val repository: MarvelRepository by lazy {
        MarvelRepositoryImpl()
    }

    init {
        loadData()
    }

    fun loadData() {
        getCreator()
        getSeries()
        getComics()
    }

    private fun getCreator() {
        repository.getCreatorById(creatorId).subscribe({
            it.toData()?.let {
                creator.postValue(Status.Success(it))
            }
        }, {
            Log.d("nahed", it.toString())
        }).add()
    }

    private fun getSeries() {
        repository.getSeriesForCreator(creatorId).subscribe({
            it.toData()?.let {
                seriesList.postValue(Status.Success(it))
                Log.d("nahed", it.toString())

            }
        }, {
            Log.d("nahed", it.toString())
        }).add()
    }

    private fun getComics() {
        repository.getComicsForCreator(creatorId).subscribe({
            it.toData()?.let {
                comicsList.postValue(Status.Success(it))
                Log.d("nahed", it.toString())

            }
        }, {
            Log.d("nahed", it.toString())
        }).add()
    }

    override fun onClickSeries(id: Int) {
       // TODO("Not yet implemented")
    }

    override fun onClickComic(id: Int) {
        //TODO("Not yet implemented")
    }
}