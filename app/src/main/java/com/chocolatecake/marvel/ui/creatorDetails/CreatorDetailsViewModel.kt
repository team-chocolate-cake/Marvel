package com.chocolatecake.marvel.ui.creatorDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel

class CreatorDetailsViewModel(
    private val creatorId: Int,
) : BaseViewModel(), CreatorDetailsListener {

    private val _comicsList = MutableLiveData<Status<List<ComicsResult>?>>()
    val comicsList: LiveData<Status<List<ComicsResult>?>>
        get() = _comicsList

    private val _seriesList = MutableLiveData<Status<List<SeriesResult>?>>()
    val seriesList: LiveData<Status<List<SeriesResult>?>>
        get() = _seriesList

    private val _creator = MutableLiveData<Status<List<ProfileResult>?>>()
    val creator: LiveData<Status<List<ProfileResult>?>>
        get() = _creator


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
                _creator.postValue(Status.Success(it))
            }
        }, {
        }).add()
    }

    private fun getSeries() {
        repository.getSeriesForCreator(creatorId).subscribe({
            it.toData()?.let {
                _seriesList.postValue(Status.Success(it))
            }
        }, {
            Log.d("nahed", it.toString())
        }).add()
    }

    private fun getComics() {
        repository.getComicsForCreator(creatorId).subscribe({
            it.toData()?.let {
                _comicsList.postValue(Status.Success(it))

            }
        }, {
            Log.d("nahed", it.toString())
        }).add()
    }

    override fun onClickSeries(id: Int) {
        navigate(CreatorDetailsFragmentDirections.actionCreatorDetailsFragmentToSeriesDetailsFragment(id))
    }

    override fun onClickComic(id: Int) {
        navigate(CreatorDetailsFragmentDirections.actionCreatorDetailsFragmentToComicsDetailsFragment(id))
    }
}