package com.chocolatecake.marvel.ui.latest_series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.series_details.view.SeriesDetailsFragmentDirections

class LatestSeriesViewModel : BaseViewModel(), SeriesInteractionListener {

    private val LIMIT = 100
    private val repository: MarvelRepository by lazy { MarvelRepositoryImpl() }

    private val _latestSeriesList = MutableLiveData<Status<List<SeriesResult>>>()
    val latestSeriesList: LiveData<Status<List<SeriesResult>>>
        get() = _latestSeriesList

    private var _id = MutableLiveData<Int>()
    val id: LiveData<Int>
        get() = _id

    init {
        getLatestSeriesList()
    }

    private fun getLatestSeriesList() {
        repository.getSeries(limit = LIMIT).subscribe({
            it.toData()?.data?.results?.let { result ->
                _latestSeriesList.postValue(
                    Status.Success(
                        result.filterNotNull().sortedByDescending { it.startYear })
                )
            }
        }, {
            _latestSeriesList.postValue(Status.Failure(it.message.toString()))
        }).add()
    }

    override fun onClickSeries(seriesId: Int) {
       // _id.postValue(seriesId)
    }
}