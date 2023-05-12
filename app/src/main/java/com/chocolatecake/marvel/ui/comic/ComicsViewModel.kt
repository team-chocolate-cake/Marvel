package com.chocolatecake.marvel.ui.comic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.core.listener.ComicListener

class ComicsViewModel : BaseViewModel(), ComicListener {

    private val repository: MarvelRepository = MarvelRepositoryImpl()

    private val _comics = MutableLiveData<Status<List<ComicsResult>>>()
    val comics: LiveData<Status<List<ComicsResult>>> get() = _comics


    init {
        loadComics()
    }

    fun loadComics() {
        val offset = (0..5000).random()
        _comics.postValue(Status.Loading)
        repository.getComics(
            limit = 100,
            offset = offset,
        )
            .subscribe(::onComicsSuccess, ::onComicsFailure)
            .add()
    }

    private fun onComicsSuccess(status: Status<List<ComicsResult>>) {
        status.toData()?.let {
            _comics.postValue(Status.Success(it))
        }
    }

    private fun onComicsFailure(throwable: Throwable) {
        _comics.postValue(Status.Failure(throwable.message.toString()))
    }


    override fun onClickComic(id: Int) {
        navigate(ComicsFragmentDirections.actionComicsFragmentToComicsDetailsFragment(id))
    }
}