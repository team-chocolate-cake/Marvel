package com.chocolatecake.marvel.ui.latest_series

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.remote.model.dto.SeriesDto
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.domain.model.Series
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.core.listener.SeriesListener
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class LatestSeriesViewModel @Inject constructor(
    private val repository: MarvelRepository
) : BaseViewModel(), SeriesListener {

    private val _latestSeriesList = MutableLiveData<Status<List<Series>>>()
    val latestSeriesList: LiveData<Status<List<Series>>>
        get() = _latestSeriesList


    init {
        loadData()
    }

    fun loadData() {
        _latestSeriesList.postValue(Status.Loading)
        repository.refreshSeries(limit = LIMIT).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({},{}).add()
        disposeObservableResponse(
            response = repository.getSeries(),
            onSuccess = ::onLatestSeriesSuccess,
            onFailure = ::onLatestSeriesFailure,
        )
    }

    private fun onLatestSeriesSuccess(status: Status<List<Series>>) {
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