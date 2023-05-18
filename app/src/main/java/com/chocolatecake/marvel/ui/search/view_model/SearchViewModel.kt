package com.chocolatecake.marvel.ui.search.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.data.remote.model.dto.SeriesDto
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.util.Status
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
    val state: LiveData<Status<SearchDataHolder>>
        get() = _state


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


    init {
        applySearch()
    }

    private fun applySearch() {
        searchQuery.debounce(500, TimeUnit.MILLISECONDS).subscribe {
//            loadData()
        }.add()
    }

//    fun loadData() {
//        _state.postValue(Status.Loading)
//        when (searchType) {
//            SearchItemType.TYPE_SERIES -> getAllSeries()
//            SearchItemType.TYPE_COMICS -> getAllComics()
//            SearchItemType.TYPE_CHARACTER -> getAllCharacters()
//        }
//    }

    //region Series
//    private fun getAllSeries() {
//        disposeResponse(
//            response = repository.getSeries(searchText),
//            onSuccess = ::onSeriesSuccess,
//            onFailure = ::onFailure,
//        )
//    }

    private fun onSeriesSuccess(seriesDto: Status<List<SeriesDto>>) {
        seriesDto.toData()?.let { result ->
            val newState = Status.Success(SearchDataHolder(series = result))
            _state.postValue(newState)
        }
    }
    //endregion

    //region Comics
    private fun getAllComics() {
        disposeResponse(
            response = repository.getComics(searchText),
            onSuccess = ::onComicsSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onComicsSuccess(comicResult: Status<List<ComicDto>>) {
        comicResult.toData()?.let { result ->
            val newState = Status.Success(SearchDataHolder(comics = result))
            _state.postValue(newState)
        }
    }
    //endregion

    //region Characters
    private fun getAllCharacters() {
        disposeResponse(
            response = repository.getCharacters(searchText),
            onSuccess = ::onCharactersSuccess,
            onFailure = ::onFailure,

        )
    }

    private fun onCharactersSuccess(characterResult: Status<List<ProfileDto>>) {
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
}