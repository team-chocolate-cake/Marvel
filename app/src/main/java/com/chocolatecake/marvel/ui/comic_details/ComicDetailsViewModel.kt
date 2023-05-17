package com.chocolatecake.marvel.ui.comic_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.remote.model.ComicDto
import com.chocolatecake.marvel.data.remote.model.ProfileDto
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class ComicDetailsViewModel @Inject constructor(
    private val repository: MarvelRepository,
    ) : BaseViewModel(), ComicInteractionListener {

    var currentComicId by Delegates.notNull<Int>()

    private val _currentComic = MutableLiveData<Status<ComicDto>?>()
    val currentComic: LiveData<Status<ComicDto>?>
        get() = _currentComic

    private val _characters = MutableLiveData<Status<List<ProfileDto>?>>()
    val characters: LiveData<Status<List<ProfileDto>?>>
        get() = _characters

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage


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
        _toastMessage.postValue(ERROR_OCCURRED)
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
        _toastMessage.postValue(ERROR_OCCURRED)
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


    companion object {
        private const val ERROR_OCCURRED = "error occurred"
    }
}
