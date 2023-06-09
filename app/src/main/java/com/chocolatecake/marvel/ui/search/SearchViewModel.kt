package com.chocolatecake.marvel.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.domain.model.Character
import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.domain.model.SearchHistory
import com.chocolatecake.marvel.domain.model.Series
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.search.model.SearchDataHolder
import com.chocolatecake.marvel.ui.search.model.SearchItemType
import com.chocolatecake.marvel.ui.search.model.SearchQuery
import com.chocolatecake.marvel.ui.search.view.SearchFragmentDirections
import com.chocolatecake.marvel.ui.search.view.SearchInteractionListener
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MarvelRepository
) : BaseViewModel(), SearchInteractionListener {

    private val searchQuery = BehaviorSubject.createDefault(SearchQuery())

    private val _state = MutableLiveData<Status<SearchDataHolder>>()
    val state: LiveData<Status<SearchDataHolder>> = _state

    private val _searchHistory = MutableLiveData<List<SearchHistory>>()
    val searchHistory: LiveData<List<SearchHistory>> = _searchHistory


    var searchText: String?
        get() = searchQuery.value?.query
        set(value) {
            searchQuery.onNext(
                SearchQuery(
                    query = value?.takeIf { it.isNotBlank() },
                    type = searchType
                )
            )

            getSearchHistory(
                keyword = value ?: "",
                type = searchType.name
            )
        }

    var searchType: SearchItemType
        get() = searchQuery.value?.type ?: SearchItemType.TYPE_SERIES
        set(value) {
            searchQuery.onNext(
                SearchQuery(query = searchText, type = value)
            )
        }

    init {
        applySearch()
    }

    private fun applySearch() {
        searchQuery.debounce(800, TimeUnit.MILLISECONDS).subscribe {
            loadData()
            insertSearchHistory(
                keyword = it.query,
                type = searchType.name
            )
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


    //region search history
    private fun getSearchHistory(keyword: String, type: String) {
        disposeObservableResponse(
            response = repository.getFilteredSearchHistory(
                keyword = keyword,
                type = type
            ),
            onSuccess = { _searchHistory.postValue(it) },
            onFailure = ::onFailure

        )
    }

    private fun insertSearchHistory(keyword: String?, type: String) {
        keyword?.let { SearchHistory(keyword = it, type = type) }?.let {
            repository.insertSearchHistory(it).subscribe()
        }
    }
    //endregion

    //region Series
    private fun getAllSeries() {
        repository.refreshSeries(title = searchText, limit = LIMIT).subscribe({},{}).add()
        disposeObservableResponse(
            response = repository.searchSeries(searchText ?: "", limit = LIMIT),
            onSuccess = ::onSeriesSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onSeriesSuccess(seriesDto: Status<List<Series>>) {
        seriesDto.toData()?.let { result ->
            val newState = Status.Success(SearchDataHolder(series = result))
            _state.postValue(newState)
            Log.e("Mooody", "getAllSeries: $newState", )
        }
    }
    //endregion

    //region Comics
    private fun getAllComics() {
        repository.refreshComics(title = searchText, limit = LIMIT).subscribe({},{}).add()
        disposeObservableResponse(
            response = repository.searchComics(searchText ?: "", limit = LIMIT),
            onSuccess = ::onComicsSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onComicsSuccess(comicResult: Status<List<Comic>>) {
        comicResult.toData()?.let { result ->
            val newState = Status.Success(SearchDataHolder(comics = result))
            _state.postValue(newState)
        }
    }
    //endregion

    //region Characters
    private fun getAllCharacters() {
        repository.refreshCharacters(name = searchText).subscribe({},{}).add()
        disposeObservableResponse(
            response = repository.getCharacters(searchText ?: ""),
            onSuccess = ::onCharactersSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onCharactersSuccess(characterResult: Status<List<Character>>) {
        characterResult.toData()?.let { result ->
            val newState = Status.Success(SearchDataHolder(characters = result))
            _state.postValue(newState)
        }
    }
    //endregion

    private fun onFailure(throwable: Throwable) {
        _state.postValue(Status.Failure(throwable.message.toString()))
    }


    override fun onClickSeries(id: Int) {
        navigate(SearchFragmentDirections.actionSearchFragmentToSeriesDetailsFragment(id))
    }

    override fun onClickComic(id: Int) {
        navigate(SearchFragmentDirections.actionSearchFragmentToComicsDetailsFragment(id))
    }

    override fun onClickCharacter(id: Int) {
        navigate(SearchFragmentDirections.actionSearchFragmentToCharacterDetailsFragment(id))
    }
    
    private companion object{
        const val LIMIT = 10
    }
}