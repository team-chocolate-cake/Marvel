package com.chocolatecake.marvel.ui.character_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.core.listener.ComicListener

class CharacterDetailsViewModel : BaseViewModel(), ComicListener {
    private val repository: MarvelRepository by lazy { MarvelRepositoryImpl() }

    private val _comics = MutableLiveData<Status<List<ComicsResult>>>()
    val comics: LiveData<Status<List<ComicsResult>>>
        get() = _comics

    private val _character = MutableLiveData<Status<ProfileResult>>()
    val character: LiveData<Status<ProfileResult>>
        get() = _character

    init {
        loadDetails()
    }

    fun loadDetails() {
        loadCharacter()
        loadComics()
    }

    private fun loadCharacter() {
        _character.postValue(Status.Loading)
        repository.getCharacterById(1017100)
            .subscribe(::onCharacterSuccess, ::onCharacterFailure)
            .add()
    }

    private fun onCharacterSuccess(status: Status<BaseResponse<ProfileResult>?>) {
        status.toData()?.data?.results?.first().let {
            _character.postValue(Status.Success(it!!))
        }
    }

    private fun loadComics() {
        _comics.postValue(Status.Loading)
        repository.getComicsForCharacter(1017100)
            .subscribe(::onComicsSuccess, ::onCharacterFailure)
            .add()
    }

    private fun onComicsSuccess(status: Status<BaseResponse<ComicsResult>?>) {
        status.toData()?.data?.results?.let {
            _comics.postValue(Status.Success(it.filterNotNull()))
        }
    }

    private fun onCharacterFailure(throwable: Throwable) {
        _character.postValue(Status.Failure(throwable.message.toString()))
        _comics.postValue(Status.Failure(throwable.message.toString()))
    }

    override fun onClickComic(id: Int) {
        Log.i("Clicked", id.toString())
    }

}