package com.chocolatecake.marvel.ui.latest_series

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.base.BaseViewModel

class LatestSeriesViewModel : BaseViewModel(), SeriesInteractionListener {
    private val repository: MarvelRepository by lazy { MarvelRepositoryImpl() }

    private val _latestSeriesList = MutableLiveData<Status<List<SeriesResult>>>()
    val latestSeriesList: LiveData<Status<List<SeriesResult>>>
        get() = _latestSeriesList

    private var _id= MutableLiveData<Int>()
    val id: LiveData<Int>
        get()= _id

    init {
        getLatestSeriesList()
    }

    private fun getLatestSeriesList(){
        repository.getSeries(limit=100).subscribe({
         it.toData()?.data?.results?.let {result ->
             _latestSeriesList.postValue(Status.Success(result.filterNotNull().sortedByDescending { it.startYear }))
//             Log.d("AMNA",result.requireNoNulls().toString())
         }
        }, {
            _latestSeriesList.postValue(Status.Failure(it.message.toString()))
        }).add()
    }

    override fun onClickSeries(seriesId: Int){
//        Log.i("AMNA",seriesId.toString())
        _id.postValue(seriesId)
    }
}