package com.chocolatecake.marvel.ui.comic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.core.listener.ComicListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val repository: MarvelRepository
) : BaseViewModel(), ComicListener {

    private val _comics = MutableLiveData<Status<List<Comic>>>()
    val comics: LiveData<Status<List<Comic>>>
        get() = _comics


    init {
        loadComics()
    }

    //regin Comics
    fun loadComics() {
        _comics.postValue(Status.Loading)
        disposeObservableResponse(
            response = repository.getComics(limit = LIMIT),
            onSuccess = ::onComicsSuccess,
            onFailure = ::onComicsFailure,
        )
    }

    private fun onComicsSuccess(status: Status<List<Comic>>) {
        status.toData()?.let { _comics.postValue(Status.Success(it)) }
    }

    private fun onComicsFailure(throwable: Throwable) {
        _comics.postValue(Status.Failure(throwable.message.toString()))
    }
    //endregion


    override fun onClickComic(id: Int) {
        navigate(ComicsFragmentDirections.actionComicsFragmentToComicsDetailsFragment(id))
    }

    private companion object {
        const val LIMIT = 100
    }
}