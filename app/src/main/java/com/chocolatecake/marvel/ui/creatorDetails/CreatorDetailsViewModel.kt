package com.chocolatecake.marvel.ui.creatorDetails

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel

class CreatorDetailsViewModel : BaseViewModel(),CreatorDetailsListener {
   val comicsList = MutableLiveData<Status<List<ComicsResult>?>>()
    val seriesList = MutableLiveData<Status<List<SeriesResult>?>>()
    val  creator=MutableLiveData<Status<List<ProfileResult>?>>()


    private val repository: MarvelRepository by lazy {
        MarvelRepositoryImpl()
    }
    init {
        getComics()
        getSeries()
        getCreator()
    }

    private fun getComics() {
        repository.getComicsForCreator(13970).subscribe({
            it.toData()?.data?.results.let {
                comicsList.postValue(Status.Success(it?.filterNotNull()))
                Log.d("nahed", it.toString())

            }
        }, {
            Log.d("nahed", it.toString())
        }).add()
    }

    private fun getSeries() {
        repository.getSeriesForCreator(13970).subscribe({
            it.toData()?.data?.results.let {
                seriesList.postValue(Status.Success(it?.filterNotNull()))
                Log.d("nahed", it.toString())

            }
        }, {
            Log.d("nahed", it.toString())
        }).add()
    }
    private fun getCreator(){
        repository.getCreatorById(13970).subscribe({
            it.toData()?.data?.results.let {
                creator.postValue(Status.Success(it?.filterNotNull()))
            }
        },{
            Log.d("nahed", it.toString())
        }).add()
    }

    override fun onClickSeries(seriesId: Int?) {
        TODO("Not yet implemented")
    }

    override fun onClickComics(comicsId: Int?) {
        TODO("Not yet implemented")
    }


}