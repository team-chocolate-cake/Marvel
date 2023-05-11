package com.chocolatecake.marvel.ui.character_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.core.listener.ComicListener

class CharacterDetailsViewModel(
    private val characterId: Int
) : BaseViewModel(), ComicListener {

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
        repository.getCharacterById(characterId)
            .subscribe(::onCharacterSuccess, ::onCharacterFailure)
            .add()
    }

    private fun onCharacterSuccess(status: Status<List<ProfileResult>>) {
        status.toData()?.first().let {
            _character.postValue(Status.Success(it!!))
        }
    }


    private fun loadComics() {
        _comics.postValue(Status.Loading)
        repository.getComicsForCharacter(characterId)
            .subscribe(::onComicsSuccess, ::onCharacterFailure)
            .add()
    }

    private fun onComicsSuccess(status: Status<List<ComicsResult>>) {
        status.toData()?.let {
            _comics.postValue(Status.Success(it))
        }
    }


    private fun onCharacterFailure(throwable: Throwable) {
        _character.postValue(Status.Failure(throwable.message.toString()))
        _comics.postValue(Status.Failure(throwable.message.toString()))
    }


    override fun onClickComic(id: Int) {
        navigate(
            CharacterDetailsFragmentDirections.actionCharacterDetailsFragmentToComicsDetailsFragment(
                id
            )
        )
    }

}