package com.chocolatecake.marvel.ui.latest_series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.remote.model.SeriesDto
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.core.listener.SeriesListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LatestSeriesViewModel @Inject constructor(
    private val repository: MarvelRepository
) : BaseViewModel(), SeriesListener {

    private val _latestSeriesList = MutableLiveData<Status<List<SeriesDto>>>()
    val latestSeriesList: LiveData<Status<List<SeriesDto>>>
        get() = _latestSeriesList


    init {
        loadData()
    }

    fun loadData() {
        _latestSeriesList.postValue(Status.Loading)
        disposeResponse(
            response = repository.getSeries(limit = LIMIT, orderBy = ORDER_BY),
            onSuccess = ::onLatestSeriesSuccess,
            onFailure = ::onLatestSeriesFailure,
        )
    }

    private fun onLatestSeriesSuccess(status: Status<List<SeriesDto>>) {
        status.toData()?.let { result ->
            _latestSeriesList.postValue(
                Status.Success(
                    result
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
        var ORDER_BY = "-startYear"
    }
}