package com.chocolatecake.marvel.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.ui.base.BaseViewModel

class SearchViewModel: BaseViewModel(){
    val repository: MarvelRepository by lazy { MarvelRepositoryImpl() }
    private val _searchQuery = MutableLiveData<String>()


    fun getAllSeries(){
      repository.getSeries(_searchQuery.value,).subscribe()

    }
    private fun onSeriesSuccess(){

    }
    fun getAllCharacters(){

    }

    fun getAllComics(){

    }






































    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    private val _selectedFilter = MutableLiveData<SearchItemType>()
    val selectedFilter: LiveData<SearchItemType> get() = _selectedFilter

    fun setSelectedFilter(filter: SearchItemType) {
        _selectedFilter.value = filter
    }




}