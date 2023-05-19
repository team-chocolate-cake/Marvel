package com.chocolatecake.marvel.ui.comic_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.domain.model.Character
import com.chocolatecake.marvel.domain.model.ComicDetails
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicDetailsViewModel @Inject constructor(
    private val repository: MarvelRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(), ComicInteractionListener {

    private val comicId =
        ComicDetailsFragmentArgs.fromSavedStateHandle(savedStateHandle).comicId

    private val _currentComic = MutableLiveData<Status<ComicDetails>>()
    val currentComic: LiveData<Status<ComicDetails>> = _currentComic

    private val _characters = MutableLiveData<Status<List<Character>?>>()
    val characters: LiveData<Status<List<Character>?>>  = _characters

    private val _toastMessage = MutableLiveData<Event<String>>()
    val toastMessage: LiveData<Event<String>> = _toastMessage

    init {
        loadData()
    }

    fun loadData() {
        getCurrentComic()
        getCharactersOfComic()
    }

    //region Current Comic
    private fun getCurrentComic() {
        _currentComic.postValue(Status.Loading)
        disposeResponse(
            response = repository.getComicById(comicId),
            onSuccess = ::onGetCurrentComicSuccess,
            onFailure = ::onGetCurrentComicFailure,
        )
    }

    private fun onGetCurrentComicSuccess(comic: Status<ComicDetails>) {
        _currentComic.postValue(comic)
    }

    private fun onGetCurrentComicFailure(throwable: Throwable) {
        _currentComic.postValue(Status.Failure(throwable.message.toString()))
        _toastMessage.postValue(Event(ERROR_OCCURRED))
    }
    //endregion

    //region Characters of Comic
    private fun getCharactersOfComic() {
        _characters.postValue(Status.Loading)
        disposeResponse(
            response = repository.getCharactersForSeries(comicId),
            onSuccess = ::onGetCharacterSuccess,
            onFailure = ::onGetCharacterFailure,
        )
    }

    private fun onGetCharacterSuccess(status: Status<List<Character>>) {
        status.toData()?.let {
            _characters.postValue(Status.Success(it))
        }
    }

    private fun onGetCharacterFailure(throwable: Throwable) {
        _toastMessage.postValue(Event(ERROR_OCCURRED))
        _characters.postValue(Status.Failure(throwable.message.toString()))
    }
    //endregion

    override fun onClickCharacter(id: Int) {
        navigate(
            ComicDetailsFragmentDirections.actionComicsDetailsFragmentToCharacterDetailsFragment(
                id
            )
        )
    }

    override fun onClickEvent(id: Int) {
        navigate(
            ComicDetailsFragmentDirections.actionComicsDetailsFragmentToEventDetailsFragment(
                id
            )
        )
    }
    private companion object {
        const val ERROR_OCCURRED = "error occurred"
    }
}
