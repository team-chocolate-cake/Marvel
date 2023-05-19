package com.chocolatecake.marvel.ui.character_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.domain.model.CharacterDetails
import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.core.listener.ComicListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val repository: MarvelRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(), ComicListener {

    private val characterId =
        CharacterDetailsFragmentArgs.fromSavedStateHandle(savedStateHandle).characterId

    private val _comics = MutableLiveData<Status<List<Comic>>>()
    val comics: LiveData<Status<List<Comic>>> = _comics

    private val _character = MutableLiveData<Status<CharacterDetails>>()
    val character: LiveData<Status<CharacterDetails>> = _character

    init {
        loadDetails()
    }

    fun loadDetails() {
        loadCharacter()
        loadComics()
    }

    //region Character
    private fun loadCharacter() {
        _character.postValue(Status.Loading)
        disposeResponse(
            response= repository.getCharacterById(characterId),
            onSuccess = ::onCharacterSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onCharacterSuccess(status: Status<CharacterDetails>) {
        status.toData()?.let {
            _character.postValue(Status.Success(it))
        }
    }
    //endregion

    //region Comics
    private fun loadComics() {
        _comics.postValue(Status.Loading)
        disposeResponse(
            response= repository.getComicsForCharacter(characterId),
            onSuccess = ::onComicsSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onComicsSuccess(status: Status<List<Comic>>) {
        status.toData()?.let { _comics.postValue(Status.Success(it)) }
    }
    //endregion

    private fun onFailure(throwable: Throwable) {
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