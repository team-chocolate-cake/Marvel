package com.chocolatecake.marvel.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel

class SearchViewModel: BaseViewModel(),SearchInteractionListener{
    private val repository: MarvelRepository by lazy { MarvelRepositoryImpl() }
     val searchQuery = MutableLiveData<String>()
    private val _searchItemId= MutableLiveData<Int?>()
    val searchItemId:LiveData<Int?>
        get() = _searchItemId
    private val _series=MutableLiveData<Status<List<SeriesResult>>>()
    val series:MutableLiveData<Status<List<SeriesResult>>>
        get()=_series
    private val _comics=MutableLiveData<Status<List<ComicsResult>>>()
    val comics:MutableLiveData<Status<List<ComicsResult>>>
        get()=_comics
    private val _character=MutableLiveData<Status<List<ProfileResult>>>()
    val character:MutableLiveData<Status<List<ProfileResult>>>
        get()=_character
    val itemsList= mutableListOf<SearchItems>()
    init {
        getAllSeries()
        getAllComics()
        getAllCharacters()
    }

    private  fun getAllSeries(){
      repository.getSeries(searchQuery.value).subscribe(::onSeriesSuccess).add()
    }
    private fun onSeriesSuccess(seriesResult :Status<BaseResponse<SeriesResult>?>){
        seriesResult.toData()?.data?.results?.let {
            _series.postValue(Status.Success(it.filterNotNull()))
        }
    }
    private  fun getAllCharacters(){
        repository.getCharacters(searchQuery.value,).subscribe(::onCharactersSuccess).add()

    }
    private fun onCharactersSuccess(characterResult :Status<BaseResponse<ProfileResult>?>){
        characterResult.toData()?.data?.results?.let {
            _character.postValue(Status.Success(it.filterNotNull()))
        }

    }
   private fun getAllComics(){
        repository.getComics(searchQuery.value,).subscribe(::onComicsSuccess).add()
    }
    private fun onComicsSuccess(comicResult :Status<BaseResponse<ComicsResult>?>){
        comicResult.toData()?.data?.results?.let {
            _comics.postValue(Status.Success(it.filterNotNull()))
        }

    }
    override fun onclick(id: Int?) {
        _searchItemId.postValue(id)
    }

}