package com.chocolatecake.marvel.ui.latest_series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.core.listener.SeriesListener
import com.chocolatecake.marvel.ui.series_details.view.SeriesDetailsFragmentDirections

class LatestSeriesViewModel : BaseViewModel(), SeriesListener {

    private val repository: MarvelRepository by lazy { MarvelRepositoryImpl() }

    private val _latestSeriesList = MutableLiveData<Status<List<SeriesResult>>>()
    val latestSeriesList: LiveData<Status<List<SeriesResult>>>
        get() = _latestSeriesList


    init {
        loadData()
    }

    fun loadData() {
        _latestSeriesList.postValue(Status.Loading)
        disposeResponse(
            response = repository.getSeries(limit = LIMIT),
            onSuccess = ::onLatestSeriesSuccess,
            onFailure = ::onLatestSeriesFailure,
        )
    }

    private fun onLatestSeriesSuccess(status: Status<List<SeriesResult>>) {
        status.toData()?.let { result ->
            _latestSeriesList.postValue(
                Status.Success(
                    result.sortedByDescending { it.startYear }
                )
            )
        }
    }

    private fun onLatestSeriesFailure(throwable: Throwable) {
        _latestSeriesList.postValue(Status.Failure(throwable.message.toString()))
    }


    override fun onClickSeries(id: Int) {
        navigate(LatestSeriesFragmentDirections.actionLatestSeriesFragmentToSeriesDetailsFragment(id))
    }


    private companion object {
        const val LIMIT = 100
    }
}
