package com.chocolatecake.marvel.ui.comic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.core.listener.ComicListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val repository: MarvelRepository
) : BaseViewModel(), ComicListener {

    private val _comics = MutableLiveData<Status<List<ComicsResult>>>()
    val comics: LiveData<Status<List<ComicsResult>>>
        get() = _comics


    init {
        loadComics()
    }

    //regin Comics
    fun loadComics() {
        _comics.postValue(Status.Loading)
        disposeResponse(
            response = repository.getComics(limit = LIMIT, offset = (0..5000).random()),
            onSuccess = ::onComicsSuccess,
            onFailure = ::onComicsFailure,
        )
    }

    private fun onComicsSuccess(status: Status<List<ComicsResult>>) {
        status.toData()?.let {
            _comics.postValue(Status.Success(it))
        }
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