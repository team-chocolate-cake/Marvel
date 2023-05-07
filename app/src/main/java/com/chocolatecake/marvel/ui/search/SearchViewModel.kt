package com.chocolatecake.marvel.ui.search

import android.util.Log
import androidx.databinding.InverseBindingListener
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
            searchQuery.onNext(SearchQuery(query = value?.takeIf { it.isNotBlank() }))
        }

    var searchType: SearchItemType
        get() = searchQuery.value?.type ?: SearchItemType.TYPE_SERIES
        set(value) {
            searchQuery.onNext(SearchQuery(type = value))
        }

    private val _searchItemId = MutableLiveData<Int?>()
    val searchItemId: LiveData<Int?>
        get() = _searchItemId

    private val _series = MutableLiveData<Status<List<SeriesResult>>>()
    val series: MutableLiveData<Status<List<SeriesResult>>>
        get() = _series

    private val _comics = MutableLiveData<Status<List<ComicsResult>>>()
    val comics: MutableLiveData<Status<List<ComicsResult>>>
        get() = _comics

    private val _character = MutableLiveData<Status<List<ProfileResult>>>()
    val character: MutableLiveData<Status<List<ProfileResult>>>
        get() = _character

    init {
        applySearch()
    }

    private fun applySearch() {
        searchQuery.debounce(500, TimeUnit.MILLISECONDS).subscribe {
            when (searchType) {
                SearchItemType.TYPE_SERIES -> getAllSeries()
                SearchItemType.TYPE_COMICS -> getAllComics()
                SearchItemType.TYPE_CHARACTER -> getAllCharacters()
            }
            Log.e("TAGTAG", "applySearch: $it", )
        }.add()
    }

    private fun getAllSeries() {
        repository.getSeries(searchText)
            .subscribe(::onSeriesSuccess, ::onFailure).add()
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

    private fun onCharactersSuccess(characterResult: Status<BaseResponse<ProfileResult>?>) {
        characterResult.toData()?.data?.results?.let {
            _character.postValue(Status.Success(it.filterNotNull()))
        }
    }

    private fun onSeriesSuccess(seriesResult: Status<BaseResponse<SeriesResult>?>) {
        seriesResult.toData()?.data?.results?.let {
            _series.postValue(Status.Success(it.filterNotNull()))
        }
    }

    private fun onComicsSuccess(comicResult: Status<BaseResponse<ComicsResult>?>) {
        comicResult.toData()?.data?.results?.let {
            _comics.postValue(Status.Success(it.filterNotNull()))
        }
    }

    private fun onFailure(throwable: Throwable) {
        _series.postValue(Status.Failure(throwable.message.toString()))
        _comics.postValue(Status.Failure(throwable.message.toString()))
        _character.postValue(Status.Failure(throwable.message.toString()))
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