package com.chocolatecake.marvel.ui.latest_series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.core.listener.SeriesListener

class LatestSeriesViewModel : BaseViewModel(), SeriesListener {

    private val repository: MarvelRepository by lazy { MarvelRepositoryImpl() }

    private val _latestSeriesList = MutableLiveData<Status<List<SeriesResult>>>()
    val latestSeriesList: LiveData<Status<List<SeriesResult>>>
        get() = _latestSeriesList

    private var _id = MutableLiveData<Int>()
    val id: LiveData<Int>
        get() = _id

    init {
        loadData()
    }

    fun loadData() {
        repository.getSeries(limit = LIMIT).subscribe({
            it.toData()?.let { result ->
                _latestSeriesList.postValue(
                    Status.Success(
                        result.sortedByDescending { it.startYear })
                )
            }
        }, {
            _latestSeriesList.postValue(Status.Failure(it.message.toString()))
        }).add()
    }

    override fun onClickSeries(id: Int) {
        _id.postValue(id)
    }

    private companion object{
        const val LIMIT = 100
    }
}
