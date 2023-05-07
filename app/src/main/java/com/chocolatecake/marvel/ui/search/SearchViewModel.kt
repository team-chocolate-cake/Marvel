package com.chocolatecake.marvel.ui.search

import android.util.Log
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
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class SearchViewModel : BaseViewModel(), SearchInteractionListener {
    private val repository: MarvelRepository by lazy { MarvelRepositoryImpl() }
    private val searchQuery = BehaviorSubject.createDefault(SearchQuery())

    var searchText: String?
        get() = searchQuery.value?.query
        set(value) {
            searchQuery.onNext(
                SearchQuery(
                    query = value?.takeIf { it.isNotBlank() },
                    type = searchType
                )
            )
        }

    var searchType: SearchItemType
        get() = searchQuery.value?.type ?: SearchItemType.TYPE_SERIES
        set(value) {
            searchQuery.onNext(SearchQuery(query = searchText, type = value))
        }

    private val _searchItemId = MutableLiveData<Int?>()
    val searchItemId: LiveData<Int?>
        get() = _searchItemId

    private val _state = MutableLiveData<Status<SearchUiState>>()
    val state: LiveData<Status<SearchUiState>>
        get() = _state

    init {
        applySearch()
    }

    private fun applySearch() {
        searchQuery.debounce(500, TimeUnit.MILLISECONDS).subscribe {
            loadData()
        }.add()
    }

    fun loadData() {
        _state.postValue(Status.Loading)
        when (searchType) {
            SearchItemType.TYPE_SERIES -> getAllSeries()
            SearchItemType.TYPE_COMICS -> getAllComics()
            SearchItemType.TYPE_CHARACTER -> getAllCharacters()
        }
    }

    private fun getAllSeries() {
        repository.getSeries(searchText)
            .subscribe(::onSeriesSuccess, ::onFailure)
            .add()
    }

    private fun getAllCharacters() {
        repository.getCharacters(searchText)
            .subscribe(::onCharactersSuccess, ::onFailure)
            .add()
    }

    private fun getAllComics() {
        repository.getComics(searchText)
            .subscribe(::onComicsSuccess, ::onFailure)
            .add()
    }

    private fun onSeriesSuccess(seriesResult: Status<BaseResponse<SeriesResult>?>) {
        seriesResult.toData()?.data?.results?.filterNotNull()?.let { result ->
            val newState = Status.Success(SearchUiState(series = result))
            _state.postValue(newState)
        }
    }

    private fun onComicsSuccess(comicResult: Status<BaseResponse<ComicsResult>?>) {
        comicResult.toData()?.data?.results?.filterNotNull()?.let { result ->
            val newState = Status.Success(SearchUiState(comics = result))
            _state.postValue(newState)
        }
    }

    private fun onCharactersSuccess(characterResult: Status<BaseResponse<ProfileResult>?>) {
        characterResult.toData()?.data?.results?.filterNotNull()?.let { result ->
            val newState = Status.Success(SearchUiState(characters = result))
            _state.postValue(newState)
        }
    }

    private fun onFailure(throwable: Throwable) {
        Log.e("TAGTAG", "onFailure: ${throwable.message}")
        _state.postValue(Status.Failure(throwable.message.toString()))
    }

    override fun onclickSeries(id: Int?) {
        _searchItemId.postValue(id)
    }

    override fun onclickComics(id: Int?) {
        _searchItemId.postValue(id)
    }

    override fun onclickCharacters(id: Int?) {
        _searchItemId.postValue(id)
    }
}