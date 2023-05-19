package com.chocolatecake.marvel.ui.comic_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicDetailsViewModel @Inject constructor(
    private val repository: MarvelRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(), ComicInteractionListener {

    private val currentComicId: Int = savedStateHandle[COMIC_ID] ?: 0

    private val _currentComic = MutableLiveData<Status<ComicDto>?>()
    val currentComic: LiveData<Status<ComicDto>?> = _currentComic

    private val _characters = MutableLiveData<Status<List<ProfileDto>?>>()
    val characters: LiveData<Status<List<ProfileDto>?>>  = _characters

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
            response = repository.getComicById(currentComicId),
            onSuccess = ::onGetCurrentComicSuccess,
            onFailure = ::onGetCurrentComicFailure,
        )
    }

    private fun onGetCurrentComicSuccess(status: Status<List<ComicDto>>) {
        status.toData()?.first()?.let {
            _currentComic.postValue(Status.Success(it))
        }
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
            response = repository.getCharactersForSeries(currentComicId),
            onSuccess = ::onGetCharacterSuccess,
            onFailure = ::onGetCharacterFailure,
        )
    }

    private fun onGetCharacterSuccess(status: Status<List<ProfileDto>>) {
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
        const val COMIC_ID = "comicId"
    }
}
