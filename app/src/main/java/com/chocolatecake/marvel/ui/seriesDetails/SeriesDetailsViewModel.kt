package com.chocolatecake.marvel.ui.seriesDetails

import android.util.Log
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.ui.base.BaseViewModel

class SeriesDetailsViewModel : BaseViewModel() {

    val repository : MarvelRepository by lazy { MarvelRepositoryImpl() }

    fun getSeriesById( ){
        repository.getSeriesById(31445)
            .subscribe({
                Log.e("Najeia", it.toData().toString())

            },{

            }).add()
    }
}